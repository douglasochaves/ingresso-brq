package br.com.brq.brqingresso.entrypoint.controllers;

import br.com.brq.brqingresso.entrypoint.mappers.UsuarioDomainMap;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioModelResponse;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.services.UsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/challengebrq/v1/usuarios")
@AllArgsConstructor
public class UsuarioControllerV2 {

    private final UsuarioUseCase usuarioUseCase;

    private final UsuarioDomainMap usuarioDomainMap;


    @PostMapping
    public ResponseEntity<UsuarioModelResponse> cadastrarUsuarioV2 (@Valid @RequestBody UsuarioModelRequest usuarioModelRequest) {
        UsuarioDomain usuarioDomain = usuarioDomainMap.mapToDomain(usuarioModelRequest);
        usuarioUseCase.cadastraUsuario(usuarioDomain);
        UsuarioModelResponse usuarioModelResponse = usuarioDomainMap.mapUsuarioResponse(usuarioDomain);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioModelResponse);
    }

}
