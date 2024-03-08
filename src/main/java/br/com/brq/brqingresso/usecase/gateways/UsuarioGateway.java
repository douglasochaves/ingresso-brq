package br.com.brq.brqingresso.usecase.gateways;

import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;

import java.util.List;

public interface UsuarioGateway {

    UsuarioDomain save(UsuarioDomain usuarioDomain);
    List<UsuarioListaDomain> findAll();
}
