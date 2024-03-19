package br.com.brq.brqingresso.mocks;

import br.com.brq.brqingresso.entrypoint.models.request.EnderecoModelRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioAtualizaModelRequest;
import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;

public class UsuarioAtualizaRequestMock {

    public static UsuarioDomain getUsuarioAtualizaRequestMock(){
        UsuarioDomain usuarioAtualizaRequest = new UsuarioDomain();
        EnderecoDomain enderecoRequest = new EnderecoDomain();

        usuarioAtualizaRequest.setCpf("41986472833");
        usuarioAtualizaRequest.setEmail("dodochaves123@gmail.com");
        usuarioAtualizaRequest.setNomeCompleto("Douglas Chaves");
        usuarioAtualizaRequest.setSenha("teste");
        usuarioAtualizaRequest.setApelido("Dodo");
        usuarioAtualizaRequest.setDataNascimento("2002-03-03");
        usuarioAtualizaRequest.setCelular(12997411365L);
        usuarioAtualizaRequest.setGenero("");

        enderecoRequest.setNumero("54");
        enderecoRequest.setCep("12234110");
        enderecoRequest.setComplemento("casa");
        usuarioAtualizaRequest.setEndereco(enderecoRequest);

        return usuarioAtualizaRequest;
    }
}
