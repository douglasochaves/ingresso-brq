//package br.com.brq.brqingresso.entrypoint.controllers;
//
//import br.com.brq.brqingresso.entrypoint.mappers.UsuarioMapper;
//import br.com.brq.brqingresso.entrypoint.models.request.AlteraSenhaRequest;
//import br.com.brq.brqingresso.entrypoint.models.request.NovaSenhaRequest;
//import br.com.brq.brqingresso.entrypoint.models.request.UsuarioAtualizaModelRequest;
//import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;
//import br.com.brq.brqingresso.mocks.UsuarioDomainMock;
//import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
//import br.com.brq.brqingresso.usecase.services.UsuarioUseCase;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class UsuarioControllerTest {
//
//    @Mock
//    private UsuarioDomain usuario;
//    @Mock
//    private UsuarioUseCase usuarioUseCase;
//    @Mock
//    private UsuarioMapper usuarioDomainMap;
//    @Mock
//    private UsuarioListaMap usuarioListaMap;
//    @Mock
//    private UsuarioAtualizaMap usuarioAtualizaMap;
//
//    @InjectMocks
//    private UsuarioController usuarioController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        this.usuario = UsuarioDomainMock.getUsuario();
//    }
//
//    @Test
//    void testCadastrarUsuario() {
//        assertNotNull(usuarioController.cadastrarUsuario(new UsuarioModelRequest()));
//    }
//
//    @Test
//    void testListarUsuario() {
//        assertNotNull(usuarioController.listarUsuario());
//    }
//
//    @Test
//    void testDetalharUsuario() {
//        assertNotNull(usuarioController.detalharUsuario(""));
//    }
//
//    @Test
//    void testExcluirUsuario() {
//        assertNotNull(usuarioController.excluirUsuario(""));
//    }
//
//    @Test
//    void testAtualizarUsuario() {
//        assertNotNull(usuarioController.atualizarUsuario(new UsuarioAtualizaModelRequest(), ""));
//    }
//
//    @Test
//    void testGerarHashTrocaSenha() {
//        when(usuarioUseCase.geraHashTrocaSenha("")).thenReturn(usuario);
//        assertNotNull(usuarioController.gerarHashTrocaSenha(""));
//    }
//
//    @Test
//    void testNovaSenha() {
//        assertNotNull(usuarioController.novaSenha(new NovaSenhaRequest(), ""));
//    }
//
//    @Test
//    void testAlterarSenha() {
//        assertNotNull(usuarioController.alterarSenha(new AlteraSenhaRequest(), ""));
//    }
//
//}