package br.com.brq.brqingresso.entrypoint.controllers;

import br.com.brq.brqingresso.entrypoint.mappers.UsuarioMapper;
import br.com.brq.brqingresso.entrypoint.models.request.AlteraSenhaRequest;
import br.com.brq.brqingresso.entrypoint.models.request.NovaSenhaRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioAtualizaModelRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;
import br.com.brq.brqingresso.entrypoint.models.response.GeraHashTrocaSenhaResponse;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioAtualizaModelResponse;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioListaModelResponse;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioModelResponse;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;
import br.com.brq.brqingresso.usecase.services.UsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/challengebrq/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;


    @PostMapping
    public ResponseEntity<UsuarioModelResponse> cadastrarUsuario (
            @Valid @RequestBody UsuarioModelRequest usuarioModelRequest) {
        UsuarioDomain usuarioDomain = UsuarioMapper.mapToDomain(usuarioModelRequest);
        UsuarioDomain usuarioCadastrado = usuarioUseCase.cadastraUsuario(usuarioDomain);
        UsuarioModelResponse usuarioModelResponse = UsuarioMapper.maptoResponse(usuarioCadastrado);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioModelResponse);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListaModelResponse>> listarUsuario () {
        List<UsuarioListaDomain> usuarioListaDomain = usuarioUseCase.listaUsuarios();
        List<UsuarioListaModelResponse> usuarioListaResponse = UsuarioMapper.mapToResponse(usuarioListaDomain);
        return ResponseEntity.ok().body(usuarioListaResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioModelResponse> detalharUsuario (@PathVariable String id) {
        UsuarioDomain usuario = usuarioUseCase.detalhaUsuario(id);
        UsuarioModelResponse usuarioResponse = UsuarioMapper.maptoResponse(usuario);
        return ResponseEntity.ok().body(usuarioResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirUsuario (@PathVariable String id) {
        usuarioUseCase.excluiUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UsuarioAtualizaModelResponse> atualizarUsuario (
            @RequestBody UsuarioAtualizaModelRequest usuarioRequest, @PathVariable String id) {
        UsuarioDomain usuarioDomain = UsuarioMapper.mapToDomain(usuarioRequest);
        UsuarioDomain usuarioAtualizado = usuarioUseCase.atualizaUsuario(usuarioDomain, id);
        UsuarioAtualizaModelResponse usuarioResponseAtualiza = UsuarioMapper.mapToResponse(usuarioAtualizado);
        return ResponseEntity.ok().body(usuarioResponseAtualiza);
    }

    @GetMapping(value = "/{id}/senhas")
    public ResponseEntity<GeraHashTrocaSenhaResponse> gerarHashTrocaSenha (@PathVariable String id) {
        UsuarioDomain usuarioDomain = usuarioUseCase.geraHashTrocaSenha(id);
        GeraHashTrocaSenhaResponse hashTrocaSenha = new GeraHashTrocaSenhaResponse();
        hashTrocaSenha.setCodigoSeguranca(usuarioDomain.getCodigoSeguranca());
        return ResponseEntity.ok().body(hashTrocaSenha);
    }

    @PostMapping(value = "/{id}/senhas")
    public ResponseEntity<Void> novaSenha (@Valid @RequestBody NovaSenhaRequest novaSenhaRequest, @PathVariable String id) {
        usuarioUseCase.novaSenha(novaSenhaRequest.getCodigoSeguranca(), novaSenhaRequest.getNovaSenha(), id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}/senhas")
    public ResponseEntity<Void> alterarSenha (@Valid @RequestBody AlteraSenhaRequest alteraSenhaRequest, @PathVariable String id) {
        usuarioUseCase.alteraSenha(alteraSenhaRequest.getSenhaAtual(), alteraSenhaRequest.getNovaSenha(), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
