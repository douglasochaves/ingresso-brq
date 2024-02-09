package br.com.brq.brqingresso.mappers.usuarioatualiza;

import br.com.brq.brqingresso.domain.usuarioatualiza.UsuarioAtualizaResponse;
import br.com.brq.brqingresso.domain.usuario.EnderecoRequest;
import br.com.brq.brqingresso.domain.usuario.EnderecoResponse;
import br.com.brq.brqingresso.domain.usuario.UsuarioRequest;
import br.com.brq.brqingresso.entities.Endereco;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.service.usuario.exception.CampoNuloException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UsuarioAtualizaMap {

    public static Usuario mapUsuarioAtualiza(UsuarioRequest usuarioRequest, Usuario usuario){

        Usuario usuarioMap = usuario;
        EnderecoRequest enderecoRequest = usuarioRequest.getEndereco();

        if(enderecoRequest != null) {
            mapAtualizaComEndereco(usuarioRequest, usuarioMap, enderecoRequest);
        } else {
            mapAtualizaSemEndereco(usuarioRequest, usuarioMap);
        }

        return usuarioMap;
    }

    private static void mapAtualizaSemEndereco(UsuarioRequest usuarioRequest, Usuario usuario) {
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

    private static void mapAtualizaComEndereco (UsuarioRequest usuarioRequest, Usuario usuario, EnderecoRequest enderecoRequest) {
        mapAtualizaSemEndereco(usuarioRequest, usuario);
        usuario.setEndereco(mapEndereco(enderecoRequest));
    }

    private static Boolean verificaCampo(Object campo) {
        if(campo != null) return true;
        throw new CampoNuloException("Todos os campos devem estar preenchidos");
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

    public static UsuarioAtualizaResponse mapUsuarioAtualizaResponse(Usuario usuario){
        UsuarioAtualizaResponse usuarioResponseMap = new UsuarioAtualizaResponse();
        Endereco endereco = usuario.getEndereco();

        ZonedDateTime dataCadastro = ZonedDateTime.of(usuario.getDataCadastro(), ZoneId.systemDefault());
        ZonedDateTime dataAtualizacao = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

        String dataCadastroString = dataCadastro.format(formatter);
        String dataAtualizacaoString = dataAtualizacao.format(formatter);

        usuarioResponseMap.setId(usuario.getId());
        usuarioResponseMap.setCpf(usuario.getCpf());
        usuarioResponseMap.setEmail(usuario.getEmail());
        usuarioResponseMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioResponseMap.setApelido(usuario.getApelido());
        usuarioResponseMap.setDataNascimento(usuario.getDataNascimento());
        usuarioResponseMap.setCelular(usuario.getCelular());
        usuarioResponseMap.setGenero(generoUsuarioResponse(usuario.getGenero()));
        usuarioResponseMap.setDataCadastro(dataCadastroString);
        usuarioResponseMap.setDataAtualizacao(dataAtualizacaoString);
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

        return enderecoResponseMap;
    }
}
