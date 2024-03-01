package br.com.brq.brqingresso.service.usuario;

import br.com.brq.brqingresso.domain.cep.CepResponse;
import br.com.brq.brqingresso.domain.usuario.UsuarioRequest;
import br.com.brq.brqingresso.domain.usuario.UsuarioResponse;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.mappers.usuario.UsuarioMap;
import br.com.brq.brqingresso.mocks.CepResponseMock;
import br.com.brq.brqingresso.mocks.UsuarioRequestMock;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import br.com.brq.brqingresso.service.cep.CepClient;
import br.com.brq.brqingresso.service.cep.CepService;
import br.com.brq.brqingresso.service.usuario.exception.errors.UsuarioInexistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private UsuarioRequest usuarioRequest;
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
        this.usuarioRequest = UsuarioRequestMock.getUsuarioRequestMock();
        this.cepResponse = CepResponseMock.getCepResponseMock();
    }

    @Test
    void testProcessUsuarioSuccess(){
        doReturn(cepResponse).when(cepService).processCep(anyString());
        assertNotNull(usuarioService.cadastraUsuario(usuarioRequest));
    }

    @Test
    void testProcessUsuarioCpfDuplicado(){
        doReturn(cepResponse).when(cepService).processCep(anyString());
        assertNotNull(usuarioService.cadastraUsuario(usuarioRequest));
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
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        UsuarioResponse usuarioResponse = usuarioService.detalhaUsuario(id);

        assertAll(
                () -> verify(usuarioRepository).findById(id),
                () -> assertNotNull(usuarioResponse)
        );
    }
}