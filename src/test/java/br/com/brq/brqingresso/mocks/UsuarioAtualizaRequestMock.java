package br.com.brq.brqingresso.mocks;

import br.com.brq.brqingresso.v1.domain.usuario.EnderecoRequest;
import br.com.brq.brqingresso.v1.domain.usuarioatualiza.UsuarioAtualizaRequest;

public class UsuarioAtualizaRequestMock {

    public static UsuarioAtualizaRequest getUsuarioAtualizaRequestMock(){
        UsuarioAtualizaRequest usuarioAtualizaRequest = new UsuarioAtualizaRequest();
        EnderecoRequest enderecoRequest = new EnderecoRequest();

        usuarioAtualizaRequest.setCpf("41986472833");
        usuarioAtualizaRequest.setEmail("dodochaves123@gmail.com");
        usuarioAtualizaRequest.setNomeCompleto("Douglas Chaves");
        usuarioAtualizaRequest.setSenha("teste");
        usuarioAtualizaRequest.setApelido("Dodo");
        usuarioAtualizaRequest.setDataNascimento("2002-03-03");
        usuarioAtualizaRequest.setCelular(12997411365L);
        usuarioAtualizaRequest.setGenero("M");

        enderecoRequest.setNumero("54");
        enderecoRequest.setCep("12234110");
        enderecoRequest.setComplemento("casa");
        usuarioAtualizaRequest.setEndereco(enderecoRequest);

        return usuarioAtualizaRequest;
    }
}
