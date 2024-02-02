package br.com.brq.brqingresso.common;

import br.com.brq.brqingresso.domain.Endereco;
import br.com.brq.brqingresso.domain.Usuario;

public class Helpers {

    public static Usuario mapUsuario(Usuario usuario){
        Usuario usuarioMap = new Usuario();
        Endereco enderecoMap = new Endereco();
        Endereco endereco = usuario.getEndereco();

        usuarioMap.setCpf(usuario.getCpf());
        usuarioMap.setEmail(usuario.getEmail());
        usuarioMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioMap.setSenha(usuario.getSenha());
        usuarioMap.setApelido(usuario.getApelido());
        usuarioMap.setData_nascimento(usuario.getData_nascimento());
        usuarioMap.setCelular(usuario.getCelular());
        usuarioMap.setGenero(usuario.getGenero());

        enderecoMap.setLogradouro(endereco.getLogradouro());
        enderecoMap.setNumero(endereco.getNumero());
        enderecoMap.setBairro(endereco.getBairro());
        enderecoMap.setCidade(endereco.getCidade());
        enderecoMap.setEstado(endereco.getEstado());
        enderecoMap.setPais(endereco.getPais());
        enderecoMap.setCep(endereco.getCep());

        usuarioMap.setEndereco(enderecoMap);

        return usuario;
    }
}
