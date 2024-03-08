package br.com.brq.brqingresso.usecase.services;

import br.com.brq.brqingresso.dataprovider.services.validations.ValidationsService;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioListaResponse;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    private final ValidationsService validationsService;

    public UsuarioDomain cadastraUsuario(UsuarioDomain usuario) {
        validationsService.verificaCadastro(usuario);
        usuarioGateway.save(usuario);
        return usuario;
    }

    public List<UsuarioListaDomain> listaUsuarios() {
        List<UsuarioListaDomain> usuarios = usuarioGateway.findAll();
        return usuarios;
    }
}
