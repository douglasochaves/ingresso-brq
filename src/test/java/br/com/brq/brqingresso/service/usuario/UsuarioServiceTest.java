package br.com.brq.brqingresso.service.usuario;

import br.com.brq.brqingresso.common.utils.Helpers;
import br.com.brq.brqingresso.domain.cep.CepResponse;
import br.com.brq.brqingresso.domain.trocasenha.AlteraSenhaRequest;
import br.com.brq.brqingresso.domain.trocasenha.GeraHashTrocaSenhaResponse;
import br.com.brq.brqingresso.domain.trocasenha.NovaSenhaRequest;
import br.com.brq.brqingresso.domain.usuario.UsuarioRequest;
import br.com.brq.brqingresso.domain.usuario.UsuarioResponse;
import br.com.brq.brqingresso.domain.usuarioatualiza.UsuarioAtualizaRequest;
import br.com.brq.brqingresso.domain.usuarioatualiza.UsuarioAtualizaResponse;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.mocks.CepResponseMock;
import br.com.brq.brqingresso.mocks.UsuarioAtualizaRequestMock;
import br.com.brq.brqingresso.mocks.UsuarioMock;
import br.com.brq.brqingresso.mocks.UsuarioRequestMock;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import br.com.brq.brqingresso.service.cep.CepService;
import br.com.brq.brqingresso.service.usuario.exception.badrequest.FormatoCodigoInvalidoException;
import br.com.brq.brqingresso.service.usuario.exception.errors.InformacaoDuplicadaException;
import br.com.brq.brqingresso.service.usuario.exception.errors.InformacaoIncompativelException;
import br.com.brq.brqingresso.service.usuario.exception.errors.TempoExcedidoException;
import br.com.brq.brqingresso.service.usuario.exception.errors.UsuarioInexistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private Usuario usuario;
    private UsuarioRequest usuarioRequest;
    private UsuarioAtualizaRequest usuarioAtualizaRequest;
    private CepResponse cepResponse;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private CepService cepService;
    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.usuario = UsuarioMock.getUsuario();
        this.usuarioRequest = UsuarioRequestMock.getUsuarioRequestMock();
        this.usuarioAtualizaRequest = UsuarioAtualizaRequestMock.getUsuarioAtualizaRequestMock();
        this.cepResponse = CepResponseMock.getCepResponseMock();
    }

    @Test
    void testCadastraUsuarioSuccess(){
        doReturn(cepResponse).when(cepService).processCep(anyString());
        assertNotNull(usuarioService.cadastraUsuario(usuarioRequest));
    }

    @Test
    void testCadastraUsuarioCpfDuplicado(){
        String cpf = "41986472833";
        when(usuarioRepository.existsByCpf(cpf)).thenReturn(true);
        doReturn(cepResponse).when(cepService).processCep(anyString());
        assertThrows(InformacaoDuplicadaException.class, () ->
                usuarioService.cadastraUsuario(usuarioRequest)
        );
    }

    @Test
    void testCadastraUsuarioEmailDuplicado(){
        String email = "dodochaves123@gmail.com";
        when(usuarioRepository.existsByEmail(email)).thenReturn(true);
        doReturn(cepResponse).when(cepService).processCep(anyString());
        assertThrows(InformacaoDuplicadaException.class, () ->
                usuarioService.cadastraUsuario(usuarioRequest)
        );
    }

    @Test
    void testDetalhaUsuarioUsuarioInexistente(){
        assertThrows(UsuarioInexistenteException.class, () ->
                usuarioService.detalhaUsuario("teste")
        );
    }

    @Test
    void testDetalhaUsuarioSuccess(){
        String id = "123";
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        UsuarioResponse usuarioResponse = usuarioService.detalhaUsuario(id);

        assertAll(
                () -> verify(usuarioRepository).findById(id),
                () -> assertNotNull(usuarioResponse)
        );
    }

    @Test
    void testExcluiUsuarioSuccess(){
        String id = "123";
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        usuarioService.excluiUsuario(id);
        verify(usuarioRepository).findById(id);
    }

    @Test
    void testAtualizaUsuarioSuccess(){
        String id = "123";
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        doReturn(cepResponse).when(cepService).processCep(anyString());
        UsuarioAtualizaResponse usuarioAtualizaResponse = usuarioService.atualizaUsuario(usuarioAtualizaRequest, id);

        assertAll(
                () -> verify(usuarioRepository).findById(id),
                () -> assertNotNull(usuarioAtualizaResponse)
        );
    }

    @Test
    void testGeraHashTrocaSenhaSuccess(){
        String id = "123";
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        GeraHashTrocaSenhaResponse geraHashTrocaSenhaResponse = usuarioService.geraHashTrocaSenha(id);

        assertAll(
                () -> verify(usuarioRepository).findById(id),
                () -> assertNotNull(geraHashTrocaSenhaResponse)
        );
    }

    @Test
    void testNovaSenhaSuccess(){
        String id = "123";
        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
        novaSenhaRequest.setCodigoSeguranca("2d08dca7-620d-4233-a496-28c6f637f8a8");
        novaSenhaRequest.setNovaSenha("novasenhateste");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        usuarioService.novaSenha(novaSenhaRequest, id);
        verify(usuarioRepository).findById(id);
    }

    @Test
    void testNovaSenhaFormatoCodigoInvalido(){
        String id = "123";
        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
        novaSenhaRequest.setCodigoSeguranca("testeerror");
        novaSenhaRequest.setNovaSenha("novasenhateste");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        assertThrows(FormatoCodigoInvalidoException.class, () ->
                        usuarioService.novaSenha(novaSenhaRequest, id)
        );
    }

    @Test
    void testNovaSenhaCodigoIncompativel(){
        String id = "123";
        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
        novaSenhaRequest.setCodigoSeguranca("2d18dca7-620f-4233-a496-28c6f637f8a8");
        novaSenhaRequest.setNovaSenha("novasenhateste");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        assertThrows(InformacaoIncompativelException.class, () ->
                usuarioService.novaSenha(novaSenhaRequest, id)
        );
    }

    @Test
    void testNovaSenhaTempoExcedido(){
        LocalDateTime dataHoraCodigoSeguranca = LocalDateTime.now().minusMinutes(6);
        String dataHoraCodigoSegurancaString = Helpers.dataHoraFormatada(dataHoraCodigoSeguranca);
        String id = "123";
        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
        novaSenhaRequest.setCodigoSeguranca("2d08dca7-620d-4233-a496-28c6f637f8a8");
        novaSenhaRequest.setNovaSenha("novasenhateste");
        usuario.setDataHoraCodigoSeguranca(dataHoraCodigoSegurancaString);
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        assertThrows(TempoExcedidoException.class, () ->
                usuarioService.novaSenha(novaSenhaRequest, id)
        );
    }

    @Test
    void testNovaSenhaSenhasIguais(){
        String id = "123";
        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
        novaSenhaRequest.setCodigoSeguranca("2d08dca7-620d-4233-a496-28c6f637f8a8");
        novaSenhaRequest.setNovaSenha("teste");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        assertThrows(InformacaoIncompativelException.class, () ->
                usuarioService.novaSenha(novaSenhaRequest, id)
        );
    }

    @Test
    void testAlteraSenhaSuccess(){
        String id = "123";
        AlteraSenhaRequest alteraSenhaRequest = new AlteraSenhaRequest();
        alteraSenhaRequest.setSenhaAtual("teste");
        alteraSenhaRequest.setNovaSenha("novasenhateste");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        usuarioService.alteraSenha(alteraSenhaRequest, id);
        verify(usuarioRepository).findById(id);
    }

    @Test
    void testAlteraSenhaSenhasNaoCorrespondem(){
        String id = "123";
        AlteraSenhaRequest alteraSenhaRequest = new AlteraSenhaRequest();
        alteraSenhaRequest.setSenhaAtual("errada");
        alteraSenhaRequest.setNovaSenha("novasenhateste");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        assertThrows(InformacaoIncompativelException.class, () ->
                usuarioService.alteraSenha(alteraSenhaRequest, id)
        );
    }
}