package br.com.brq.brqingresso.entrypoint.mappers;

import br.com.brq.brqingresso.dataprovider.entities.EnderecoEntity;
import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.entrypoint.models.response.CepResponse;
import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

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
        usuarioMap.setDataCadastro(LocalDateTime.now());
        usuarioMap.setEndereco(mapEndereco(endereco, cepResponse));

        return usuarioMap;
    }

    private static EnderecoEntity mapEndereco(EnderecoDomain endereco, CepResponse cepResponse){
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
}
