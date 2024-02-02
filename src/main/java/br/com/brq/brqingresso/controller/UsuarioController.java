package br.com.brq.brqingresso.controller;

import br.com.brq.brqingresso.common.Helpers;
import br.com.brq.brqingresso.domain.Usuario;
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
    Usuario usuarios(@RequestBody Usuario usuario) {

        Usuario usuarioData = Helpers.mapUsuario(usuario);

        usuarioRepository.save(usuarioData);

        return usuarioData;
    }
}
