package br.com.brq.brqingresso.entrypoint.mappers;

import br.com.brq.brqingresso.common.Helpers;
import br.com.brq.brqingresso.dataprovider.entities.EnderecoEntity;
import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.entrypoint.models.response.CepResponse;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioAtualizaModelResponse;
import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UsuarioAtualizaMap {

    public static UsuarioEntity mapUsuarioAtualiza(
            UsuarioDomain usuarioAtualiza, UsuarioEntity usuarioEntity, CepResponse cepResponse){

        UsuarioEntity usuarioMap = usuarioEntity;
        EnderecoDomain enderecoAtualiza = usuarioAtualiza.getEndereco();

        if(enderecoAtualiza != null) {
            mapAtualizaComEndereco(usuarioAtualiza, usuarioMap, enderecoAtualiza, cepResponse);
        } else {
            mapAtualizaSemEndereco(usuarioAtualiza, usuarioMap);
        }

        return usuarioMap;
    }

    private static void mapAtualizaSemEndereco(UsuarioDomain usuarioAtualiza, UsuarioEntity usuarioEntity) {
        if(usuarioAtualiza.getCpf() != null)
            usuarioEntity.setCpf(usuarioAtualiza.getCpf());
        if(usuarioAtualiza.getEmail() != null)
            usuarioEntity.setEmail(usuarioAtualiza.getEmail());
        if (usuarioAtualiza.getNomeCompleto() != null)
            usuarioEntity.setNomeCompleto(usuarioAtualiza.getNomeCompleto());
        if (usuarioAtualiza.getApelido() != null)
            usuarioEntity.setApelido(usuarioAtualiza.getApelido());
        if (usuarioAtualiza.getDataNascimento() != null)
            usuarioEntity.setDataNascimento(usuarioAtualiza.getDataNascimento());
        if (usuarioAtualiza.getCelular() != null)
            usuarioEntity.setCelular(usuarioAtualiza.getCelular());
        if (usuarioAtualiza.getGenero() != null)
            usuarioEntity.setGenero(usuarioAtualiza.getGenero());
        usuarioEntity.setDataAtualizacao(LocalDateTime.now());
    }

    private static void mapAtualizaComEndereco (
            UsuarioDomain usuarioRequest,
            UsuarioEntity usuario,
            EnderecoDomain enderecoRequest,
            CepResponse cepResponse
    ) {
        mapAtualizaSemEndereco(usuarioRequest, usuario);
        usuario.setEndereco(mapEndereco(enderecoRequest, cepResponse));
    }

    private static EnderecoEntity mapEndereco(EnderecoDomain endereco, CepResponse cepResponse){
        EnderecoEntity enderecoMap = new EnderecoEntity();

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

    public UsuarioAtualizaModelResponse mapUsuarioResponse(UsuarioDomain usuario){
        UsuarioAtualizaModelResponse usuarioResponseMap = new UsuarioAtualizaModelResponse();
        EnderecoDomain endereco = usuario.getEndereco();

        usuarioResponseMap.setId(usuario.getId());
        usuarioResponseMap.setCpf(usuario.getCpf());
        usuarioResponseMap.setEmail(usuario.getEmail());
        usuarioResponseMap.setNomeCompleto(usuario.getNomeCompleto());
        usuarioResponseMap.setApelido(usuario.getApelido());
        usuarioResponseMap.setDataNascimento(usuario.getDataNascimento());
        usuarioResponseMap.setCelular(usuario.getCelular());
        usuarioResponseMap.setGenero(generoUsuarioResponse(usuario.getGenero()));
        usuarioResponseMap.setDataCadastro(Helpers.dataHoraFormatada(usuario.getDataCadastro()));
        usuarioResponseMap.setDataAtualizacao(Helpers.dataHoraAtualFormatada());
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
