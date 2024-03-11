package br.com.brq.brqingresso.usecase.services;

import br.com.brq.brqingresso.entrypoint.models.response.UsuarioListaResponse;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;

import java.util.List;

public interface UsuarioUseCase {

    UsuarioDomain cadastraUsuario(UsuarioDomain usuario);
    List<UsuarioListaDomain> listaUsuarios();
    UsuarioDomain detalhaUsuario(String id);
    void excluiUsuario(String id);
}
