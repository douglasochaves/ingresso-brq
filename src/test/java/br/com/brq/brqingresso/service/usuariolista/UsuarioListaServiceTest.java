package br.com.brq.brqingresso.service.usuariolista;

import br.com.brq.brqingresso.domain.usuariolista.UsuarioListaResponse;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsuarioListaServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;
    @InjectMocks
    private UsuarioListaService usuarioListaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscaUsuariosSuccess() {
        List<Usuario> usuariosMock = new ArrayList<>();
        usuariosMock.add(new Usuario());

        when(usuarioRepository.findAll()).thenReturn(usuariosMock);

        List<UsuarioListaResponse> usuarioListaResponse = usuarioListaService.buscaUsuarios();

        assertEquals(usuariosMock.size(), usuarioListaResponse.size());
    }
}