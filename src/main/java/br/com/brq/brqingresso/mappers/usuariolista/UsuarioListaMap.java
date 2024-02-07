package br.com.brq.brqingresso.mappers.usuariolista;

import br.com.brq.brqingresso.domain.usuariolista.UsuarioListaResponse;
import br.com.brq.brqingresso.entities.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioListaMap {

    public static List<UsuarioListaResponse> mapUsuarioListaResponse (List<Usuario> usuarios) {
        return usuarios.stream().map(usuario ->
                new UsuarioListaResponse(
                        usuario.getId(),
                        usuario.getCpf(),
                        usuario.getEmail(),
                        usuario.getNomeCompleto()
                )).collect(Collectors.toList());
    }
}
