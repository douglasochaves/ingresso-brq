package br.com.brq.brqingresso.entrypoint.mappers;

import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioListaModelResponse;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioListaMap {

    public List<UsuarioListaDomain> mapToUsuarioListaDomain (List<UsuarioEntity> usuarios) {
        return usuarios.stream().map(usuario ->
                new UsuarioListaDomain(
                        usuario.getId(),
                        usuario.getCpf(),
                        usuario.getEmail(),
                        usuario.getNomeCompleto()
                )).collect(Collectors.toList());
    }

    public List<UsuarioListaModelResponse> mapToUsuarioListaResponse (List<UsuarioListaDomain> usuarios) {
        return usuarios.stream().map(usuario ->
                new UsuarioListaModelResponse(
                        usuario.getId(),
                        usuario.getCpf(),
                        usuario.getEmail(),
                        usuario.getNomeCompleto()
                )).collect(Collectors.toList());
    }
}
