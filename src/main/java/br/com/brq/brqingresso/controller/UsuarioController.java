package br.com.brq.brqingresso.controller;

import br.com.brq.brqingresso.domain.UsuarioRequest;
import br.com.brq.brqingresso.domain.UsuarioResponse;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.mappers.usuario.UsuarioMap;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/challengebrq/v1")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "/usuarios")
    public UsuarioResponse usuario (@RequestBody UsuarioRequest usuarioRequest) {

        Usuario usuarioData = UsuarioMap.mapUsuario(usuarioRequest);

        usuarioRepository.save(usuarioData);

        UsuarioResponse usuarioResponse = UsuarioMap.mapUsuarioResponse(usuarioData);

        return usuarioResponse;
    }
}
