package br.com.brq.brqingresso.controller;

import br.com.brq.brqingresso.domain.usuariolista.UsuarioListaResponse;
import br.com.brq.brqingresso.domain.usuario.UsuarioRequest;
import br.com.brq.brqingresso.domain.usuario.UsuarioResponse;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.mappers.usuario.UsuarioMap;
import br.com.brq.brqingresso.service.usuario.UsuarioService;
import br.com.brq.brqingresso.service.usuariolista.UsuarioListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/challengebrq/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioListaService usuarioListaService;

    @PostMapping(value = "/usuarios")
    public ResponseEntity cadastrarUsuario (@RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuarioData = UsuarioMap.mapUsuario(usuarioRequest);
        usuarioService.processUsuario(usuarioData);
        UsuarioResponse usuarioResponse = UsuarioMap.mapUsuarioResponse(usuarioData);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

    @GetMapping(value = "/usuarios")
    public ResponseEntity<List<UsuarioListaResponse>> listarUsuario () {
        List<UsuarioListaResponse> usuarioListaResponse = usuarioListaService.buscaUsuarios();
        return ResponseEntity.ok().body(usuarioListaResponse);
    }
}
