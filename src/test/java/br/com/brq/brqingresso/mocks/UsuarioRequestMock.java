package br.com.brq.brqingresso.mocks;

import br.com.brq.brqingresso.v1.domain.usuario.EnderecoRequest;
import br.com.brq.brqingresso.v1.domain.usuario.UsuarioRequest;

public class UsuarioRequestMock {

    public static UsuarioRequest getUsuarioRequestMock(){
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        EnderecoRequest enderecoRequest = new EnderecoRequest();

        usuarioRequest.setCpf("41986472833");
        usuarioRequest.setEmail("dodochaves123@gmail.com");
        usuarioRequest.setNomeCompleto("Douglas Chaves");
        usuarioRequest.setSenha("teste");
        usuarioRequest.setApelido("Dodo");
        usuarioRequest.setDataNascimento("2002-03-03");
        usuarioRequest.setCelular(12997411365L);
        usuarioRequest.setGenero("M");

        enderecoRequest.setNumero("54");
        enderecoRequest.setCep("12234110");
        enderecoRequest.setComplemento("casa");
        usuarioRequest.setEndereco(enderecoRequest);

        return usuarioRequest;
    }
}
