package br.com.brq.brqingresso.entrypoint.mappers;

import br.com.brq.brqingresso.dataprovider.entities.EnderecoEntity;
import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.usecase.domains.EnderecoV2;
import br.com.brq.brqingresso.usecase.domains.UsuarioV2;

import java.time.LocalDateTime;
import java.util.UUID;

public class UsuarioMapV2 {

    public static UsuarioEntity mapUsuarioEntity(UsuarioV2 usuario){
        UsuarioEntity usuarioMap = new UsuarioEntity();
        EnderecoV2 endereco = usuario.getEndereco();

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

    private static EnderecoEntity mapEndereco(EnderecoV2 endereco){
        EnderecoEntity enderecoMap = new EnderecoEntity();

        enderecoMap.setNumero(endereco.getNumero());
        enderecoMap.setPais("BR");
        enderecoMap.setComplemento(endereco.getComplemento());

        return enderecoMap;
    }
}
