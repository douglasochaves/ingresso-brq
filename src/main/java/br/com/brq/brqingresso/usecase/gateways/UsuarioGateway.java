package br.com.brq.brqingresso.usecase.gateways;

import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;

public interface UsuarioGateway {

    UsuarioDomain save(UsuarioDomain usuarioV2);
}
