package br.com.brq.brqingresso.mappers.usuarioatualiza;

import br.com.brq.brqingresso.common.utils.Helpers;
import br.com.brq.brqingresso.domain.cep.CepResponse;
import br.com.brq.brqingresso.domain.usuario.EnderecoRequest;
import br.com.brq.brqingresso.domain.usuario.EnderecoResponse;
import br.com.brq.brqingresso.domain.usuarioatualiza.UsuarioAtualizaRequest;
import br.com.brq.brqingresso.domain.usuarioatualiza.UsuarioAtualizaResponse;
import br.com.brq.brqingresso.entities.Endereco;
import br.com.brq.brqingresso.entities.Usuario;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UsuarioAtualizaMap {

    public static Usuario mapUsuarioAtualiza(UsuarioAtualizaRequest usuarioRequest, Usuario usuario, CepResponse cepResponse){

        Usuario usuarioMap = usuario;
        EnderecoRequest enderecoRequest = usuarioRequest.getEndereco();

        if(enderecoRequest != null) {
            mapAtualizaComEndereco(usuarioRequest, usuarioMap, enderecoRequest, cepResponse);
        } else {
            mapAtualizaSemEndereco(usuarioRequest, usuarioMap);
        }

        return usuarioMap;
    }

    private static void mapAtualizaSemEndereco(UsuarioAtualizaRequest usuarioRequest, Usuario usuario) {
        if (verificaCampoEnderecoNulo(usuarioRequest.getNomeCompleto()))
            usuario.setNomeCompleto(usuarioRequest.getNomeCompleto());
        if (verificaCampoEnderecoNulo(usuarioRequest.getApelido()))
            usuario.setApelido(usuarioRequest.getApelido());
        if (verificaCampoEnderecoNulo(usuarioRequest.getDataNascimento()))
            usuario.setDataNascimento(usuarioRequest.getDataNascimento());
        if (verificaCampoEnderecoNulo(usuarioRequest.getCelular()))
            usuario.setCelular(usuarioRequest.getCelular());
        if (verificaCampoEnderecoNulo(usuarioRequest.getGenero()))
            usuario.setGenero(generoUsuario(usuarioRequest.getGenero()));
        usuario.setDataAtualizacao(LocalDateTime.now());
    }

    private static void mapAtualizaComEndereco (
            UsuarioAtualizaRequest usuarioRequest, Usuario usuario, EnderecoRequest enderecoRequest, CepResponse cepResponse
    ) {
        mapAtualizaSemEndereco(usuarioRequest, usuario);
        usuario.setEndereco(mapEndereco(enderecoRequest, cepResponse));
    }

    private static Boolean verificaCampoEnderecoNulo(Object campo) {
        if(campo != null) return true;
        return false;
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
        enderecoMap.setCep(endereco.getCep());
        enderecoMap.setComplemento(endereco.getComplemento());

        return enderecoMap;
    }

    public static UsuarioAtualizaResponse mapUsuarioAtualizaResponse(Usuario usuario){
        UsuarioAtualizaResponse usuarioResponseMap = new UsuarioAtualizaResponse();
        Endereco endereco = usuario.getEndereco();

        ZonedDateTime dataCadastro = ZonedDateTime.of(usuario.getDataCadastro(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

        String dataCadastroString = dataCadastro.format(formatter);

        usuarioResponseMap.setId(usuario.getId());
        usuarioResponseMap.setCpf(usuario.getCpf());
        usuarioResponseMap.setEmail(usuario.getEmail());
        usuarioResponseMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioResponseMap.setApelido(usuario.getApelido());
        usuarioResponseMap.setDataNascimento(usuario.getDataNascimento());
        usuarioResponseMap.setCelular(usuario.getCelular());
        usuarioResponseMap.setGenero(generoUsuarioResponse(usuario.getGenero()));
        usuarioResponseMap.setDataCadastro(dataCadastroString);
        usuarioResponseMap.setDataAtualizacao(Helpers.dataHoraAtualFormatada());
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
