package br.com.brq.brqingresso.entrypoint.controllers;

import br.com.brq.brqingresso.dataprovider.services.UsuarioDataBaseImpl;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioV2Map;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioModelResponse;
import br.com.brq.brqingresso.usecase.domains.UsuarioV2;
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

    private final UsuarioDataBaseImpl usuarioDataBase;

    @PostMapping
    public ResponseEntity<UsuarioModelResponse> cadastrarUsuarioV2 (@Valid @RequestBody UsuarioModelRequest usuarioModelRequest) {
        UsuarioV2 usuario = UsuarioV2Map.mapUsuario(usuarioModelRequest);
        usuarioDataBase.cadastraUsuario(usuario);
        UsuarioModelResponse usuarioModelResponse = UsuarioV2Map.mapUsuarioResponse(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioModelResponse);
    }

}
