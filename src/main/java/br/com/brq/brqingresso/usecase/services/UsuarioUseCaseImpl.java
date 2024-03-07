package br.com.brq.brqingresso.usecase.services;

import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;


    public UsuarioDomain cadastraUsuario(UsuarioDomain usuario) {
        usuarioGateway.save(usuario);
        return usuario;
    }
}
