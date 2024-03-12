package br.com.brq.brqingresso.common.mocks;

import br.com.brq.brqingresso.entrypoint.models.request.EnderecoModelRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioAtualizaModelRequest;

public class UsuarioAtualizaRequestMock {

    public static UsuarioAtualizaModelRequest getUsuarioAtualizaRequestMock(){
        UsuarioAtualizaModelRequest usuarioAtualizaRequest = new UsuarioAtualizaModelRequest();
        EnderecoModelRequest enderecoRequest = new EnderecoModelRequest();

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
