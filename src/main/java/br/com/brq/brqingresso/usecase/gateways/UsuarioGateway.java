package br.com.brq.brqingresso.usecase.gateways;

import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;

import java.util.List;

public interface UsuarioGateway {

    UsuarioDomain save(UsuarioDomain usuarioDomain);
    List<UsuarioListaDomain> findAll();
    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);
    UsuarioDomain findById(String id);
    void delete(UsuarioDomain usuarioDomain);
    UsuarioDomain patch(UsuarioDomain usuario, UsuarioDomain usuarioAtualizado);
    UsuarioDomain saveHash(UsuarioDomain usuarioDomain, String hash);
    void putSenha(UsuarioDomain usuarioDomain, String novaSenha);
}
