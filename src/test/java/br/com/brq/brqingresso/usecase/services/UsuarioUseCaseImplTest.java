package br.com.brq.brqingresso.usecase.services;

import br.com.brq.brqingresso.dataprovider.services.CepService;
import br.com.brq.brqingresso.dataprovider.services.validations.ValidationsService;
import br.com.brq.brqingresso.usecase.domains.CepDomain;
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
    private CepDomain cepResponse;
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
        when(usuarioGateway.patch(usuario)).thenReturn(usuario);
        doReturn(cepResponse).when(cepService).processCep(Mockito.anyString());
        UsuarioDomain usuarioAtualizado = usuarioService.atualizaUsuario(usuario, usuario.getId());

        assertAll(
                () -> verify(usuarioGateway).patch(usuario),
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

}