package br.com.brq.brqingresso.usecase.services;

import br.com.brq.brqingresso.dataprovider.services.CepService;
import br.com.brq.brqingresso.dataprovider.services.validations.ValidationsService;
import br.com.brq.brqingresso.entrypoint.models.response.CepResponse;
import br.com.brq.brqingresso.mocks.CepResponseMock;
import br.com.brq.brqingresso.mocks.UsuarioDomainMock;
import br.com.brq.brqingresso.mocks.UsuarioListaDomainMock;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UsuarioUseCaseImplTest {

    private UsuarioDomain usuario;
    private UsuarioListaDomain usuarioLista;
    private CepResponse cepResponse;
    @Mock
    private ValidationsService validationsService;
    @Mock
    private UsuarioGateway usuarioGateway;
    @Mock
    private CepService cepService;
    @InjectMocks
    private UsuarioUseCaseImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.usuario = UsuarioDomainMock.getUsuario();
        this.usuarioLista = UsuarioListaDomainMock.getUsuarioLista();
        this.cepResponse = CepResponseMock.getCepResponseMock();
    }

    @Test
    void testCadastraUsuarioSuccess(){
        doReturn(cepResponse).when(cepService).processCep(Mockito.anyString());
        assertNotNull(usuarioService.cadastraUsuario(usuario));
    }

    @Test
    void testListaUsuarioSuccess(){
        List<UsuarioListaDomain> usuarios = Arrays.asList(usuarioLista);
        when(usuarioGateway.findAll()).thenReturn(usuarios);
        assertNotNull(usuarioService.listaUsuarios());
    }

    @Test
    void testDetalhaUsuarioSuccess(){
        String id = "123";
        when(usuarioService.detalhaUsuario(id)).thenReturn(usuario);
        UsuarioDomain usuarioResponse = usuarioService.detalhaUsuario(id);

        assertNotNull(usuarioResponse);
    }
//
    @Test
    void testExcluiUsuarioSuccess() {
        when(validationsService.verificaUsuario(anyString())).thenReturn(usuario);
        usuarioService.excluiUsuario(usuario.getId());

        verify(usuarioGateway).delete(usuario);
    }

    @Test
    void testAtualizaUsuarioSuccess(){
        when(validationsService.verificaAtualizacao(usuario, "123")).thenReturn(usuario);
        when(usuarioGateway.patch(usuario, usuario)).thenReturn(usuario);
        doReturn(cepResponse).when(cepService).processCep(Mockito.anyString());
        UsuarioDomain usuarioAtualizado = usuarioService.atualizaUsuario(usuario, usuario.getId());

        assertAll(
                () -> verify(usuarioGateway).patch(usuario, usuario),
                () -> assertNotNull(usuarioAtualizado)
        );
    }

    @Test
    void testGeraHashTrocaSenhaSuccess(){
        when(validationsService.verificaUsuario(usuario.getId())).thenReturn(usuario);
        when(usuarioGateway.saveHash(any(UsuarioDomain.class), anyString())).thenReturn(usuario);
        UsuarioDomain usuarioCadastrado = usuarioService.geraHashTrocaSenha(usuario.getId());

        assertAll(
                () -> verify(usuarioGateway).saveHash(any(UsuarioDomain.class), anyString()),
                () -> assertNotNull(usuarioCadastrado)
        );
    }

    @Test
    void testNovaSenhaSuccess(){
        usuarioService.novaSenha("", "", "");
        verify(validationsService).verificaNovaSenha("", "", "");
    }

    @Test
    void testAlteraSenhaSuccess(){
        when(validationsService.verificaAlteraSenha("at", usuario.getId())).thenReturn(usuario);
        usuarioService.alteraSenha("at", "ns", usuario.getId());
        Mockito.verify(usuarioGateway).putSenha(usuario, "ns");
    }

//    @Test
//    void testCadastraUsuarioCpfDuplicado(){
//        String cpf = "41986472833";
//        when(usuarioGateway.save(usuario)).thenThrow(new InformacaoDuplicadaException(""));
//        doReturn(cepResponse).when(cepService).processCep(Mockito.anyString());
//        assertThrows(InformacaoDuplicadaException.class, () ->
//                usuarioService.cadastraUsuario(usuario)
//        );
//    }

    ////
//    @Test
//    void testCadastraUsuarioEmailDuplicado(){
//        String email = "dodochaves123@gmail.com";
//        Mockito.when(usuarioRepository.existsByEmail(email)).thenReturn(true);
//        doReturn(cepResponse).when(cepService).processCep(Mockito.anyString());
//        assertThrows(InformacaoDuplicadaException.class, () ->
//                usuarioService.cadastraUsuario(usuario)
//        );
//    }
//
//    @Test
//    void testDetalhaUsuarioUsuarioInexistente(){
//        assertThrows(UsuarioInexistenteException.class, () ->
//                usuarioService.detalhaUsuario("teste")
//        );
//    }
//
//
//    @Test
//    void testNovaSenhaFormatoCodigoInvalido(){
//        String id = "123";
//        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
//        novaSenhaRequest.setCodigoSeguranca("testeerror");
//        novaSenhaRequest.setNovaSenha("novasenhateste");
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//
//        assertThrows(FormatoCodigoInvalidoException.class, () ->
//                        usuarioService.novaSenha(novaSenhaRequest.getCodigoSeguranca(), novaSenhaRequest.getNovaSenha(), id)
//        );
//    }
//
//    @Test
//    void testNovaSenhaCodigoIncompativel(){
//        String id = "123";
//        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
//        novaSenhaRequest.setCodigoSeguranca("2d18dca7-620f-4233-a496-28c6f637f8a8");
//        novaSenhaRequest.setNovaSenha("novasenhateste");
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//
//        Assertions.assertThrows(InformacaoIncompativelException.class, () ->
//                usuarioService.novaSenha(novaSenhaRequest.getCodigoSeguranca(), novaSenhaRequest.getNovaSenha(), id)
//        );
//    }
//
//    @Test
//    void testNovaSenhaTempoExcedido(){
//        LocalDateTime dataHoraCodigoSeguranca = LocalDateTime.now().minusMinutes(6);
//        String dataHoraCodigoSegurancaString = Helpers.dataHoraFormatada(dataHoraCodigoSeguranca);
//        String id = "123";
//        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
//        novaSenhaRequest.setCodigoSeguranca("2d08dca7-620d-4233-a496-28c6f637f8a8");
//        novaSenhaRequest.setNovaSenha("novasenhateste");
//        usuario.setDataHoraCodigoSeguranca(dataHoraCodigoSegurancaString);
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//
//        assertThrows(TempoExcedidoException.class, () ->
//                usuarioService.novaSenha(novaSenhaRequest.getCodigoSeguranca(), novaSenhaRequest.getNovaSenha(), id)
//        );
//    }
//
//    @Test
//    void testNovaSenhaSenhasIguais(){
//        String id = "123";
//        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
//        novaSenhaRequest.setCodigoSeguranca("2d08dca7-620d-4233-a496-28c6f637f8a8");
//        novaSenhaRequest.setNovaSenha("teste");
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//
//        assertThrows(InformacaoIncompativelException.class, () ->
//                usuarioService.novaSenha(novaSenhaRequest.getCodigoSeguranca(), novaSenhaRequest.getNovaSenha(), id)
//        );
//    }
//

//
//    @Test
//    void testAlteraSenhaSenhasNaoCorrespondem(){
//        String id = "123";
//        AlteraSenhaRequest alteraSenhaRequest = new AlteraSenhaRequest();
//        alteraSenhaRequest.setSenhaAtual("errada");
//        alteraSenhaRequest.setNovaSenha("novasenhateste");
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//
//        assertThrows(InformacaoIncompativelException.class, () ->
//                usuarioService.alteraSenha(alteraSenhaRequest.getSenhaAtual(), alteraSenhaRequest.getNovaSenha(), id)
//        );
//    }
}