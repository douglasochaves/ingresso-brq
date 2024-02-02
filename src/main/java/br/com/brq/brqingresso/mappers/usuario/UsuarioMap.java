package br.com.brq.brqingresso.mappers.usuario;

import br.com.brq.brqingresso.domain.EnderecoRequest;
import br.com.brq.brqingresso.domain.EnderecoResponse;
import br.com.brq.brqingresso.domain.UsuarioRequest;
import br.com.brq.brqingresso.domain.UsuarioResponse;
import br.com.brq.brqingresso.entities.Endereco;
import br.com.brq.brqingresso.entities.Usuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UsuarioMap {

    public static Usuario mapUsuario(UsuarioRequest usuario){
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
        usuarioMap.setGenero(usuario.getGenero());
        usuarioMap.setDataCadastro(LocalDateTime.now());
        usuarioMap.setEndereco(mapEndereco(endereco));

        return usuarioMap;
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

    public static UsuarioResponse mapUsuarioResponse(Usuario usuario){
        UsuarioResponse usuarioResponseMap = new UsuarioResponse();
        Endereco endereco = usuario.getEndereco();


        ZonedDateTime zonedDateTime = ZonedDateTime.of(usuario.getDataCadastro(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

        String dataCadastroString = zonedDateTime.format(formatter);

        usuarioResponseMap.setId(usuario.getId());
        usuarioResponseMap.setCpf(usuario.getCpf());
        usuarioResponseMap.setEmail(usuario.getEmail());
        usuarioResponseMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioResponseMap.setApelido(usuario.getApelido());
        usuarioResponseMap.setDataNascimento(usuario.getDataNascimento());
        usuarioResponseMap.setCelular(usuario.getCelular());
        usuarioResponseMap.setGenero(usuario.getGenero());
        usuarioResponseMap.setDataCadastro(dataCadastroString);
        usuarioResponseMap.setEndereco(mapEnderecoResponse(endereco));

        return usuarioResponseMap;
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
