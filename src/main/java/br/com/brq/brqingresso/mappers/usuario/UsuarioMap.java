package br.com.brq.brqingresso.mappers.usuario;

import br.com.brq.brqingresso.common.utils.Helpers;
import br.com.brq.brqingresso.domain.cep.CepResponse;
import br.com.brq.brqingresso.domain.usuario.EnderecoRequest;
import br.com.brq.brqingresso.domain.usuario.EnderecoResponse;
import br.com.brq.brqingresso.domain.usuario.UsuarioRequest;
import br.com.brq.brqingresso.domain.usuario.UsuarioResponse;
import br.com.brq.brqingresso.entities.Endereco;
import br.com.brq.brqingresso.entities.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public class UsuarioMap {

    public static Usuario mapUsuario(UsuarioRequest usuario, CepResponse cepResponse){
        Usuario usuarioMap = new Usuario();
        EnderecoRequest endereco = usuario.getEndereco();

        usuarioMap.setId(UUID.randomUUID().toString());
        usuarioMap.setCpf(usuario.getCpf());
        usuarioMap.setEmail(usuario.getEmail());
        usuarioMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioMap.setSenha(usuario.getSenha());
        usuarioMap.setApelido(usuario.getApelido());
        usuarioMap.setDataNascimento(usuario.getDataNascimento());
        usuarioMap.setCelular(usuario.getCelular());
        usuarioMap.setGenero(generoUsuario(usuario.getGenero()));
        usuarioMap.setDataCadastro(LocalDateTime.now());
        usuarioMap.setEndereco(mapEndereco(endereco, cepResponse));

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

    private static Endereco mapEndereco(EnderecoRequest endereco, CepResponse cepResponse){
        Endereco enderecoMap = new Endereco();

        enderecoMap.setLogradouro(cepResponse.getLogradouro());
        enderecoMap.setNumero(endereco.getNumero());
        enderecoMap.setBairro(cepResponse.getBairro());
        enderecoMap.setCidade(cepResponse.getLocalidade());
        enderecoMap.setEstado(cepResponse.getUf());
        enderecoMap.setPais("BR");
        enderecoMap.setCep(cepResponse.getCep());
        enderecoMap.setComplemento(endereco.getComplemento());

        return enderecoMap;
    }

    public static UsuarioResponse mapUsuarioResponse(Usuario usuario){
        UsuarioResponse usuarioResponseMap = new UsuarioResponse();
        Endereco endereco = usuario.getEndereco();

        usuarioResponseMap.setId(usuario.getId());
        usuarioResponseMap.setCpf(usuario.getCpf());
        usuarioResponseMap.setEmail(usuario.getEmail());
        usuarioResponseMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioResponseMap.setApelido(usuario.getApelido());
        usuarioResponseMap.setDataNascimento(usuario.getDataNascimento());
        usuarioResponseMap.setCelular(usuario.getCelular());
        usuarioResponseMap.setGenero(generoUsuarioResponse(usuario.getGenero()));
        usuarioResponseMap.setDataCadastro(Helpers.dataHoraAtualFormatada());
        usuarioResponseMap.setEndereco(mapEnderecoResponse(endereco));

        return usuarioResponseMap;
    }

    private static String generoUsuarioResponse(Integer genero) {
        switch (genero) {
            case 1:
                return "M";
            case 2:
                return "F";
            case 3:
                return "B";
            case 4:
                return "N";
            default:
                return null;
        }
    }

    private static EnderecoResponse mapEnderecoResponse(Endereco endereco){
        EnderecoResponse enderecoResponseMap = new EnderecoResponse();

        enderecoResponseMap.setLogradouro(endereco.getLogradouro());
        enderecoResponseMap.setNumero(endereco.getNumero());
        enderecoResponseMap.setBairro(endereco.getBairro());
        enderecoResponseMap.setCidade(endereco.getCidade());
        enderecoResponseMap.setEstado(endereco.getEstado());
        enderecoResponseMap.setPais(endereco.getPais());
        enderecoResponseMap.setCep(endereco.getCep());
        enderecoResponseMap.setComplemento(endereco.getComplemento());

        return enderecoResponseMap;
    }



}
