package br.com.brq.brqingresso.mappers.usuarioatualiza;

import br.com.brq.brqingresso.domain.usuario.EnderecoRequest;
import br.com.brq.brqingresso.domain.usuario.UsuarioRequest;
import br.com.brq.brqingresso.entities.Endereco;
import br.com.brq.brqingresso.entities.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public class UsuarioAtualizaMap {

    public static Usuario mapUsuarioAtualiza(UsuarioRequest usuario, String id){
        Usuario usuarioMap = new Usuario();
        EnderecoRequest endereco = usuario.getEndereco();

        usuarioMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioMap.setApelido(usuario.getApelido());
        usuarioMap.setDataNascimento(usuario.getDataNascimento());
        usuarioMap.setCelular(usuario.getCelular());
        usuarioMap.setGenero(generoUsuario(usuario.getGenero()));
        usuarioMap.setDataAtualizacao(LocalDateTime.now());
        usuarioMap.setEndereco(mapEndereco(endereco));

        return usuarioMap;
    }

    private static Integer generoUsuario(String genero) {
        switch (genero) {
            case "M":
                return 1;
            case "F":
                return 2;
            case "B":
                return 3;
            case "N":
                return 4;
            default:
                return null;
        }
    }

    private static Endereco mapEndereco(EnderecoRequest endereco){
        Endereco enderecoMap = new Endereco();

        enderecoMap.setLogradouro(endereco.getLogradouro());
        enderecoMap.setNumero(endereco.getNumero());
        enderecoMap.setBairro(endereco.getBairro());
        enderecoMap.setCidade(endereco.getCidade());
        enderecoMap.setEstado(endereco.getEstado());
        enderecoMap.setPais(endereco.getPais());
        enderecoMap.setCep(endereco.getCep());

        return enderecoMap;
    }
}
