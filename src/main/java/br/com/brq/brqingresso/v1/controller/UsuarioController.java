package br.com.brq.brqingresso.v1.controller;

import br.com.brq.brqingresso.v1.domain.trocasenha.AlteraSenhaRequest;
import br.com.brq.brqingresso.v1.domain.trocasenha.GeraHashTrocaSenhaResponse;
import br.com.brq.brqingresso.v1.domain.trocasenha.NovaSenhaRequest;
import br.com.brq.brqingresso.v1.domain.usuario.UsuarioRequest;
import br.com.brq.brqingresso.v1.domain.usuario.UsuarioResponse;
import br.com.brq.brqingresso.v1.domain.usuarioatualiza.UsuarioAtualizaRequest;
import br.com.brq.brqingresso.v1.domain.usuarioatualiza.UsuarioAtualizaResponse;
import br.com.brq.brqingresso.v1.domain.usuariolista.UsuarioListaResponse;
import br.com.brq.brqingresso.v1.service.usuario.UsuarioService;
import br.com.brq.brqingresso.v1.service.usuariolista.UsuarioListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/challengebrq/v2/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioListaService usuarioListaService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario (@Valid @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = usuarioService.cadastraUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListaResponse>> listarUsuario () {
        List<UsuarioListaResponse> usuarioListaResponse = usuarioListaService.buscaUsuarios();
        return ResponseEntity.ok().body(usuarioListaResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> detalharUsuario (@PathVariable String id) {
        UsuarioResponse usuarioResponse = usuarioService.detalhaUsuario(id);
        return ResponseEntity.ok().body(usuarioResponse);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirUsuario (@PathVariable String id) {
        usuarioService.excluiUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UsuarioAtualizaResponse> atualizarUsuario (@Valid @RequestBody UsuarioAtualizaRequest usuarioRequest, @PathVariable String id) {
        UsuarioAtualizaResponse usuarioResponseAtualiza = usuarioService.atualizaUsuario(usuarioRequest, id);
        return ResponseEntity.ok().body(usuarioResponseAtualiza);
    }

    @GetMapping(value = "/{id}/senhas")
    public ResponseEntity<GeraHashTrocaSenhaResponse> gerarHashTrocaSenha (@PathVariable String id) {
        GeraHashTrocaSenhaResponse hashTrocaSenha = usuarioService.geraHashTrocaSenha(id);
        return ResponseEntity.ok().body(hashTrocaSenha);
    }

    @PostMapping(value = "/{id}/senhas")
    public ResponseEntity<Void> novaSenha (@Valid @RequestBody NovaSenhaRequest novaSenhaRequest, @PathVariable String id) {
        usuarioService.novaSenha(novaSenhaRequest, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping(value = "/{id}/senhas")
    public ResponseEntity<Void> alterarSenha (@Valid @RequestBody AlteraSenhaRequest alteraSenhaRequest, @PathVariable String id) {
        usuarioService.alteraSenha(alteraSenhaRequest, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
