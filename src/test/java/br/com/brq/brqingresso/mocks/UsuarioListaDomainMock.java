package br.com.brq.brqingresso.mocks;

import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;

public class UsuarioListaDomainMock {

    public static UsuarioListaDomain getUsuarioLista() {
        UsuarioListaDomain usuario = new UsuarioListaDomain();

        usuario.setId("123");
        usuario.setCpf("41986472833");
        usuario.setEmail("dodochaves123@gmail.com");
        usuario.setNomeCompleto("Douglas Chaves");

        return usuario;
    }
}
