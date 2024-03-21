package br.com.brq.brqingresso.usecase.mappers;

import br.com.brq.brqingresso.entrypoint.exception.badrequest.CamposAusentesException;
import br.com.brq.brqingresso.usecase.domains.CepDomain;
import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioMapper {

    public static UsuarioDomain mapEndereco(UsuarioDomain usuarioDomain, CepDomain cepDomain) {
        EnderecoDomain endereco = usuarioDomain.getEndereco();
        endereco.setCep(cepDomain.getCep());
        endereco.setEstado(cepDomain.getUf());
        endereco.setCidade(cepDomain.getLocalidade());
        endereco.setBairro(cepDomain.getBairro());
        endereco.setLogradouro(cepDomain.getLogradouro());
        endereco.setComplemento(cepDomain.getComplemento());
        usuarioDomain.setEndereco(endereco);

        return usuarioDomain;
    }

    public static UsuarioDomain mapAtualizaSemEndereco (UsuarioDomain usuarioAtualiza, UsuarioDomain usuario) {
        if(usuarioAtualiza.getCpf() != null)
            usuario.setCpf(usuarioAtualiza.getCpf());
        if(usuarioAtualiza.getEmail() != null)
            usuario.setEmail(usuarioAtualiza.getEmail());
        if (usuarioAtualiza.getNomeCompleto() != null)
            usuario.setNomeCompleto(usuarioAtualiza.getNomeCompleto());
        if (usuarioAtualiza.getApelido() != null)
            usuario.setApelido(usuarioAtualiza.getApelido());
        if (usuarioAtualiza.getDataNascimento() != null)
            usuario.setDataNascimento(usuarioAtualiza.getDataNascimento());
        if (usuarioAtualiza.getCelular() != null)
            usuario.setCelular(usuarioAtualiza.getCelular());
        if (usuarioAtualiza.getGenero() != null)
            usuario.setGenero(usuarioAtualiza.getGenero());
        usuario.setDataAtualizacao(LocalDateTime.now());

        return usuario;
    }

    public static UsuarioDomain mapAtualizaComEndereco(UsuarioDomain usuarioAtualiza, UsuarioDomain usuarioDomain) {
        if(usuarioAtualiza.getCpf() != null) {
            usuarioDomain.setCpf(usuarioAtualiza.getCpf());
        } else {
            lancaExcecao("cpf");
        }
        if(usuarioAtualiza.getEmail() != null) {
            usuarioDomain.setEmail(usuarioAtualiza.getEmail());
        } else {
            lancaExcecao("email");
        }
        if (usuarioAtualiza.getNomeCompleto() != null) {
            usuarioDomain.setNomeCompleto(usuarioAtualiza.getNomeCompleto());
        } else {
            lancaExcecao("nome_completo");
        }
        if (usuarioAtualiza.getApelido() != null) {
            usuarioDomain.setApelido(usuarioAtualiza.getApelido());
        } else {
            lancaExcecao("apelido");
        }
        if (usuarioAtualiza.getDataNascimento() != null) {
            usuarioDomain.setDataNascimento(usuarioAtualiza.getDataNascimento());
        } else {
            lancaExcecao("data_nascimento");
        }
        if (usuarioAtualiza.getCelular() != null) {
            usuarioDomain.setCelular(usuarioAtualiza.getCelular());
        } else {
            lancaExcecao("celular");
        }
        if (usuarioAtualiza.getGenero() != null) {
            usuarioDomain.setGenero(usuarioAtualiza.getGenero());
        } else {
            lancaExcecao("genero");
        }
        usuarioDomain.setDataAtualizacao(LocalDateTime.now());

        return usuarioDomain;
    }

    private static void lancaExcecao(String campo) {
        throw new CamposAusentesException(
                "Todos os campos devem estar preenchidos.",
                campo,
                "O campo " + campo + " deve estar preenchido."
        );
    }

}
