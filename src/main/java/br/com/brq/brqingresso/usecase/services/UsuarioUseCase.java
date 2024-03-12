package br.com.brq.brqingresso.usecase.services;

import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;

import java.util.List;

public interface UsuarioUseCase {

    UsuarioDomain cadastraUsuario(UsuarioDomain usuarioDomain);
    List<UsuarioListaDomain> listaUsuarios();
    UsuarioDomain detalhaUsuario(String id);
    void excluiUsuario(String id);
    UsuarioDomain atualizaUsuario(UsuarioDomain usuarioDomain, String id);
    UsuarioDomain geraHashTrocaSenha(String id);
    void novaSenha(String codigoSeguranca, String novaSenha, String id);
    void alteraSenha(String senhaAtual, String novaSenha, String id);
}
