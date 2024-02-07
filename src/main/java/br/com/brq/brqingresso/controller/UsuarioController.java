package br.com.brq.brqingresso.controller;

import br.com.brq.brqingresso.domain.UsuarioRequest;
import br.com.brq.brqingresso.domain.UsuarioResponse;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.mappers.usuario.UsuarioMap;
import br.com.brq.brqingresso.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/challengebrq/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/usuarios")
    public ResponseEntity cadastrarUsuario (@RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuarioData = UsuarioMap.mapUsuario(usuarioRequest);
        usuarioService.processUsuario(usuarioData);
        UsuarioResponse usuarioResponse = UsuarioMap.mapUsuarioResponse(usuarioData);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

    
}
