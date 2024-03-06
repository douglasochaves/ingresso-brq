package br.com.brq.brqingresso.mocks;

import br.com.brq.brqingresso.v1.common.utils.Helpers;
import br.com.brq.brqingresso.v1.entities.Endereco;
import br.com.brq.brqingresso.v1.entities.Usuario;

import java.time.LocalDateTime;

public class UsuarioMock {

    public static Usuario getUsuario() {
        Usuario usuario = new Usuario();
        Endereco endereco = new Endereco();

        usuario.setId("123");
        usuario.setCpf("41986472833");
        usuario.setEmail("dodochaves123@gmail.com");
        usuario.setNomeCompleto("Douglas Chaves");
        usuario.setSenha("teste");
        usuario.setApelido("Dodo");
        usuario.setDataNascimento("2002-03-03");
        usuario.setCelular(12997411365L);
        usuario.setGenero(1);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setCodigoSeguranca("2d08dca7-620d-4233-a496-28c6f637f8a8");
        usuario.setDataHoraCodigoSeguranca(Helpers.dataHoraAtualFormatada());

        endereco.setNumero("54");
        endereco.setCep("12234110");
        endereco.setComplemento("casa");
        usuario.setEndereco(endereco);

        return usuario;
    }
}
