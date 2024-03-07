package br.com.brq.brqingresso.entrypoint.mappers;

import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioModelResponse;
import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.v1.common.utils.Helpers;

import java.time.LocalDateTime;
import java.util.UUID;

public class UsuarioDomainMap {

    public UsuarioDomain mapToDomain(UsuarioModelRequest usuario){
        UsuarioDomain usuarioMap = new UsuarioDomain();
        EnderecoDomain endereco = usuario.getEndereco();

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

    private static EnderecoDomain mapEndereco(EnderecoDomain endereco){
        EnderecoDomain enderecoMap = new EnderecoDomain();

        enderecoMap.setNumero(endereco.getNumero());
        enderecoMap.setPais("BR");
        enderecoMap.setComplemento(endereco.getComplemento());

        return enderecoMap;
    }

    public UsuarioModelResponse mapUsuarioResponse(UsuarioDomain usuario){
        UsuarioModelResponse usuarioResponseMap = new UsuarioModelResponse();
        EnderecoDomain endereco = usuario.getEndereco();

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
        if(genero == null) return null;
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

    private static EnderecoDomain mapEnderecoResponse(EnderecoDomain endereco){
        if(endereco == null) return null;
        EnderecoDomain enderecoResponseMap = new EnderecoDomain();

        enderecoResponseMap.setNumero(endereco.getNumero());
        enderecoResponseMap.setPais(endereco.getPais());
        enderecoResponseMap.setComplemento(endereco.getComplemento());

        return enderecoResponseMap;
    }
}