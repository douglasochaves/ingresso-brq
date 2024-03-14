package br.com.brq.brqingresso.entrypoint.mappers;

import br.com.brq.brqingresso.dataprovider.entities.EnderecoEntity;
import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.entrypoint.models.response.CepResponse;
import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UsuarioEntityMap {

    public UsuarioEntity mapToEntity(UsuarioDomain usuario, CepResponse cepResponse){
        UsuarioEntity usuarioMap = new UsuarioEntity();
        EnderecoDomain endereco = usuario.getEndereco();

        usuarioMap.setId(usuario.getId());
        usuarioMap.setCpf(usuario.getCpf());
        usuarioMap.setEmail(usuario.getEmail());
        usuarioMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioMap.setSenha(usuario.getSenha());
        usuarioMap.setApelido(usuario.getApelido());
        usuarioMap.setDataNascimento(usuario.getDataNascimento());
        usuarioMap.setCelular(usuario.getCelular());
        usuarioMap.setGenero(usuario.getGenero());
        usuarioMap.setDataCadastro(usuario.getDataCadastro());
        usuarioMap.setEndereco(mapEndereco(endereco, cepResponse));

        return usuarioMap;
    }

    private static EnderecoEntity mapEndereco(EnderecoDomain endereco, CepResponse cepResponse){
        if(endereco == null || cepResponse == null) return null;
        EnderecoEntity enderecoMap = new EnderecoEntity();

        enderecoMap.setLogradouro(cepResponse.getLogradouro());
        enderecoMap.setNumero(endereco.getNumero());
        enderecoMap.setBairro(cepResponse.getBairro());
        enderecoMap.setCidade(cepResponse.getLocalidade());
        enderecoMap.setEstado(cepResponse.getUf());
        enderecoMap.setCep(endereco.getCep());
        enderecoMap.setPais("BR");
        enderecoMap.setComplemento(endereco.getComplemento());

        return enderecoMap;
    }

    public UsuarioEntity mapToEntity(UsuarioDomain usuario){
        UsuarioEntity usuarioMap = new UsuarioEntity();
        EnderecoDomain endereco = usuario.getEndereco();

        usuarioMap.setId(usuario.getId());
        usuarioMap.setCpf(usuario.getCpf());
        usuarioMap.setEmail(usuario.getEmail());
        usuarioMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioMap.setSenha(usuario.getSenha());
        usuarioMap.setApelido(usuario.getApelido());
        usuarioMap.setDataNascimento(usuario.getDataNascimento());
        usuarioMap.setCelular(usuario.getCelular());
        usuarioMap.setGenero(usuario.getGenero());
        usuarioMap.setDataCadastro(LocalDateTime.now());
        usuarioMap.setCodigoSeguranca(usuario.getCodigoSeguranca());
        usuarioMap.setDataHoraCodigoSeguranca(usuario.getDataHoraCodigoSeguranca());
        usuarioMap.setEndereco(mapEndereco(endereco));

        return usuarioMap;
    }

    private static EnderecoEntity mapEndereco(EnderecoDomain endereco){
        EnderecoEntity enderecoMap = new EnderecoEntity();

        enderecoMap.setLogradouro(endereco.getLogradouro());
        enderecoMap.setNumero(endereco.getNumero());
        enderecoMap.setBairro(endereco.getBairro());
        enderecoMap.setCidade(endereco.getCidade());
        enderecoMap.setEstado(endereco.getEstado());
        enderecoMap.setCep(endereco.getCep());
        enderecoMap.setPais("BR");
        enderecoMap.setComplemento(endereco.getComplemento());

        return enderecoMap;
    }

}
