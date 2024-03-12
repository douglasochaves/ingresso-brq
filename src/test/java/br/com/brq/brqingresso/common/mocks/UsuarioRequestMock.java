package br.com.brq.brqingresso.common.mocks;

import br.com.brq.brqingresso.dataprovider.entities.EnderecoEntity;
import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.entrypoint.models.request.EnderecoModelRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;

public class UsuarioRequestMock {

    public static UsuarioEntity getUsuarioRequestMock(){
        UsuarioEntity usuarioRequest = new UsuarioEntity();
        EnderecoEntity enderecoRequest = new EnderecoEntity();

        usuarioRequest.setCpf("41986472833");
        usuarioRequest.setEmail("dodochaves123@gmail.com");
        usuarioRequest.setNomeCompleto("Douglas Chaves");
        usuarioRequest.setSenha("teste");
        usuarioRequest.setApelido("Dodo");
        usuarioRequest.setDataNascimento("2002-03-03");
        usuarioRequest.setCelular(12997411365L);
        usuarioRequest.setGenero(1);

        enderecoRequest.setNumero("54");
        enderecoRequest.setCep("12234110");
        enderecoRequest.setComplemento("casa");
        usuarioRequest.setEndereco(enderecoRequest);

        return usuarioRequest;
    }
}
