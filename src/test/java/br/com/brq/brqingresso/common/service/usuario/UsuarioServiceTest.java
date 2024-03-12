package br.com.brq.brqingresso.common.service.usuario;

import br.com.brq.brqingresso.common.Helpers;
import br.com.brq.brqingresso.common.mocks.CepResponseMock;
import br.com.brq.brqingresso.common.mocks.UsuarioAtualizaRequestMock;
import br.com.brq.brqingresso.common.mocks.UsuarioMock;
import br.com.brq.brqingresso.common.mocks.UsuarioRequestMock;
import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.dataprovider.repositories.UsuarioRepository;
import br.com.brq.brqingresso.dataprovider.services.CepService;
import br.com.brq.brqingresso.dataprovider.services.validations.ValidationsService;
import br.com.brq.brqingresso.entrypoint.exception.badrequest.FormatoCodigoInvalidoException;
import br.com.brq.brqingresso.entrypoint.exception.errors.InformacaoDuplicadaException;
import br.com.brq.brqingresso.entrypoint.exception.errors.InformacaoIncompativelException;
import br.com.brq.brqingresso.entrypoint.exception.errors.TempoExcedidoException;
import br.com.brq.brqingresso.entrypoint.exception.errors.UsuarioInexistenteException;
import br.com.brq.brqingresso.entrypoint.models.request.AlteraSenhaRequest;
import br.com.brq.brqingresso.entrypoint.models.request.NovaSenhaRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioAtualizaModelRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;
import br.com.brq.brqingresso.entrypoint.models.response.CepResponse;
import br.com.brq.brqingresso.entrypoint.models.response.GeraHashTrocaSenhaResponse;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioAtualizaModelResponse;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioModelResponse;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import br.com.brq.brqingresso.usecase.services.UsuarioUseCaseImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    private UsuarioDomain usuario;
    private UsuarioEntity usuarioRequest;
    private UsuarioAtualizaModelRequest usuarioAtualizaRequest;
    private CepResponse cepResponse;
    @Mock
    private ValidationsService validationsService;
    @Mock
    private UsuarioGateway usuarioGateway;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private CepService cepService;
    @InjectMocks
    private UsuarioUseCaseImpl usuarioService;

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
        doReturn(cepResponse).when(cepService).processCep(Mockito.anyString());
        assertNotNull(usuarioService.cadastraUsuario(usuario));
    }

//    @Test
//    void testCadastraUsuarioCpfDuplicado(){
//        String cpf = "41986472833";
//        when(usuarioGateway.existsByCpf(cpf)).thenReturn(true);
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
//    @Test
//    void testDetalhaUsuarioSuccess(){
//        String id = "123";
//        when(usuarioGateway.findById(id)).thenReturn(usuario);
//        UsuarioDomain usuarioResponse = usuarioService.detalhaUsuario(id);
//
//        assertAll(
//                () -> Mockito.verify(usuarioGateway).findById(id),
//                () -> assertNotNull(usuarioResponse)
//        );
//    }
//
//    @Test
//    void testExcluiUsuarioSuccess(){
//        String id = "123";
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//        usuarioService.excluiUsuario(id);
//        Mockito.verify(usuarioRepository).findById(id);
//    }
//
//    @Test
//    void testAtualizaUsuarioSuccess(){
//        String id = "123";
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//        doReturn(cepResponse).when(cepService).processCep(Mockito.anyString());
//        UsuarioDomain usuarioAtualizaResponse = usuarioService.atualizaUsuario(usuario, id);
//
//        assertAll(
//                () -> Mockito.verify(usuarioRepository).findById(id),
//                () -> assertNotNull(usuarioAtualizaResponse)
//        );
//    }
//
//    @Test
//    void testGeraHashTrocaSenhaSuccess(){
//        String id = "123";
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//        UsuarioDomain geraHashTrocaSenhaResponse = usuarioService.geraHashTrocaSenha(id);
//
//        assertAll(
//                () -> Mockito.verify(usuarioRepository).findById(id),
//                () -> assertNotNull(geraHashTrocaSenhaResponse)
//        );
//    }
//
//    @Test
//    void testNovaSenhaSuccess(){
//        String id = "123";
//        NovaSenhaRequest novaSenhaRequest = new NovaSenhaRequest();
//        novaSenhaRequest.setCodigoSeguranca("2d08dca7-620d-4233-a496-28c6f637f8a8");
//        novaSenhaRequest.setNovaSenha("novasenhateste");
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//        usuarioService.novaSenha(novaSenhaRequest.getCodigoSeguranca(), novaSenhaRequest.getNovaSenha(), id);
//        Mockito.verify(usuarioRepository).findById(id);
//    }
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
//    @Test
//    void testAlteraSenhaSuccess(){
//        String id = "123";
//        AlteraSenhaRequest alteraSenhaRequest = new AlteraSenhaRequest();
//        alteraSenhaRequest.setSenhaAtual("teste");
//        alteraSenhaRequest.setNovaSenha("novasenhateste");
//        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioRequest));
//        usuarioService.alteraSenha(alteraSenhaRequest.getSenhaAtual(), alteraSenhaRequest.getNovaSenha(), id);
//        Mockito.verify(usuarioRepository).findById(id);
//    }
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