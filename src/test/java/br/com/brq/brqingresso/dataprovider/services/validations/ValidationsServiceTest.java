package br.com.brq.brqingresso.dataprovider.services.validations;

import br.com.brq.brqingresso.entrypoint.exception.badrequest.DataNascimentoInvalidaException;
import br.com.brq.brqingresso.entrypoint.exception.badrequest.FormatoCodigoInvalidoException;
import br.com.brq.brqingresso.entrypoint.exception.errors.InformacaoDuplicadaException;
import br.com.brq.brqingresso.entrypoint.exception.errors.InformacaoIncompativelException;
import br.com.brq.brqingresso.entrypoint.exception.errors.TempoExcedidoException;
import br.com.brq.brqingresso.entrypoint.exception.errors.UsuarioInexistenteException;
import br.com.brq.brqingresso.mocks.UsuarioDomainMock;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ValidationsServiceTest {

    private UsuarioDomain usuario;

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private ValidationsService validationsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.usuario = UsuarioDomainMock.getUsuario();
    }

    @Test
    void testVerificaCadastroSuccess() {
        validationsService.verificaCadastro(usuario);
        verify(usuarioGateway).existsByEmail(usuario.getEmail());
    }

    @Test
    void testVerificaCadastroDataNascimentoInvalida() {
        usuario.setDataNascimento("2030-03-03");
        assertThrows(DataNascimentoInvalidaException.class, () ->
                validationsService.verificaCadastro(usuario)
        );
    }

    @Test
    void testVerificaCadastroCpfDuplicado(){
        when(usuarioGateway.existsByCpf(usuario.getCpf())).thenReturn(true);
        assertThrows(InformacaoDuplicadaException.class, () ->
                validationsService.verificaCadastro(usuario)
        );
    }

    @Test
    void testVerificaCadastroEmailDuplicado(){
        when(usuarioGateway.existsByEmail(usuario.getEmail())).thenReturn(true);
        assertThrows(InformacaoDuplicadaException.class, () ->
                validationsService.verificaCadastro(usuario)
        );
    }

    @Test
    void testVerificaUsuarioSuccess() {
        when(usuarioGateway.findById(usuario.getId())).thenReturn(usuario);
        assertNotNull(validationsService.verificaUsuario(usuario.getId()));
    }

    @Test
    void testVerificaUsuarioUsuarioInexistente(){
        when(usuarioGateway.findById(usuario.getId())).thenReturn(null);
        assertThrows(UsuarioInexistenteException.class, () ->
                validationsService.verificaUsuario(usuario.getId())
        );
    }

    @Test
    void testVerificaAtualizacaoSuccess() {
        when(usuarioGateway.findById(usuario.getId())).thenReturn(usuario);
        assertNotNull(validationsService.verificaAtualizacao(usuario, usuario.getId()));
    }

    @Test
    void testVerificaNovaSenhaSuccess() {
        when(usuarioGateway.findById(usuario.getId())).thenReturn(usuario);
        assertNotNull(validationsService.verificaNovaSenha(
                "2d08dca7-620d-4233-a496-28c6f637f8a8", "", usuario.getId()));
    }

    @Test
    void testVerificaNovaSenhaFormatoCodigoInvalido(){
        when(usuarioGateway.findById(usuario.getId())).thenReturn(usuario);
        assertThrows(FormatoCodigoInvalidoException.class, () ->
                validationsService.verificaNovaSenha("","", usuario.getId())
        );
    }

    @Test
    void testVerificaNovaSenhaCodigoIncompativel(){
        when(usuarioGateway.findById(usuario.getId())).thenReturn(usuario);
        assertThrows(InformacaoIncompativelException.class, () ->
                validationsService.verificaNovaSenha(
                        "b6a69b06-5464-41db-a5e5-5c0b0abcc475","", usuario.getId())
        );
    }

    @Test
    void testVerificaNovaSenhaSenhancompativel(){
        when(usuarioGateway.findById(usuario.getId())).thenReturn(usuario);
        assertThrows(InformacaoIncompativelException.class, () ->
                validationsService.verificaNovaSenha(
                        "2d08dca7-620d-4233-a496-28c6f637f8a8","teste", usuario.getId())
        );
    }

    @Test
    void testVerificaNovaSenhaTempoExcedido(){
        usuario.setDataHoraCodigoSeguranca("2020-03-12T16:13:41-03:00");
        when(usuarioGateway.findById(usuario.getId())).thenReturn(usuario);
        assertThrows(TempoExcedidoException.class, () ->
                validationsService.verificaNovaSenha(
                        "2d08dca7-620d-4233-a496-28c6f637f8a8","", usuario.getId())
        );
    }

    @Test
    void testVerificaAlteraSenhaSuccess() {
        when(usuarioGateway.findById(usuario.getId())).thenReturn(usuario);
        assertNotNull(validationsService.verificaAlteraSenha( "teste", usuario.getId()));
    }

    @Test
    void testVerificaAlteraSenhaSenhaAtualIncompativel(){
        when(usuarioGateway.findById(usuario.getId())).thenReturn(usuario);
        assertThrows(InformacaoIncompativelException.class, () ->
                validationsService.verificaAlteraSenha(
                         "", usuario.getId())
        );
    }
}