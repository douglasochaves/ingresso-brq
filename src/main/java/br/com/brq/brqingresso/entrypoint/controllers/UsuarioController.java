package br.com.brq.brqingresso.entrypoint.controllers;

import br.com.brq.brqingresso.entrypoint.mappers.UsuarioDomainMap;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioListaMap;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioListaResponse;
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

    private final UsuarioDomainMap usuarioDomainMap;

    private final UsuarioListaMap usuarioListaMap;
    @PostMapping
    public ResponseEntity<UsuarioModelResponse> cadastrarUsuario (@Valid @RequestBody UsuarioModelRequest usuarioModelRequest) {
        UsuarioDomain usuarioDomain = usuarioDomainMap.mapToDomain(usuarioModelRequest);
        UsuarioDomain usuarioCadastrado = usuarioUseCase.cadastraUsuario(usuarioDomain);
        UsuarioModelResponse usuarioModelResponse = usuarioDomainMap.mapUsuarioResponse(usuarioCadastrado);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioModelResponse);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListaResponse>> listarUsuario () {
        List<UsuarioListaDomain> usuarioListaDomain = usuarioUseCase.listaUsuarios();
        List<UsuarioListaResponse> usuarioListaResponse = usuarioListaMap.mapToUsuarioListaResponse(usuarioListaDomain);
        return ResponseEntity.ok().body(usuarioListaResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioModelResponse> detalharUsuario (@PathVariable String id) {
        UsuarioDomain usuario = usuarioUseCase.detalhaUsuario(id);
        UsuarioModelResponse usuarioResponse = usuarioDomainMap.mapUsuarioResponse(usuario);
        return ResponseEntity.ok().body(usuarioResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirUsuario (@PathVariable String id) {
        usuarioUseCase.excluiUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
