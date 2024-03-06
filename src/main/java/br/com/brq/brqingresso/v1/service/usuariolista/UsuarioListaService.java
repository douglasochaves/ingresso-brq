package br.com.brq.brqingresso.v1.service.usuariolista;

import br.com.brq.brqingresso.v1.domain.usuariolista.UsuarioListaResponse;
import br.com.brq.brqingresso.v1.entities.Usuario;
import br.com.brq.brqingresso.v1.mappers.usuariolista.UsuarioListaMap;
import br.com.brq.brqingresso.v1.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioListaService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<UsuarioListaResponse> buscaUsuarios () {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioListaResponse> usuariosListaResponse = UsuarioListaMap.mapUsuarioListaResponse(usuarios);
        return usuariosListaResponse;
    }
}
