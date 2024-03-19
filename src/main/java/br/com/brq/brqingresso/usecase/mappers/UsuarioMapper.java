package br.com.brq.brqingresso.usecase.mappers;

import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.entrypoint.models.request.EnderecoModelRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;
import br.com.brq.brqingresso.usecase.domains.CepDomain;
import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.gateways.CepGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioMapper {

    public static UsuarioDomain mapEndereco(UsuarioDomain usuarioDomain, CepDomain cepDomain) {
        EnderecoDomain endereco = usuarioDomain.getEndereco();
        endereco.setCep(cepDomain.getCep());
        endereco.setEstado(cepDomain.getUf());
        endereco.setCidade(cepDomain.getLocalidade());
        endereco.setBairro(cepDomain.getBairro());
        endereco.setLogradouro(cepDomain.getLogradouro());
        endereco.setComplemento(cepDomain.getComplemento());
        usuarioDomain.setEndereco(endereco);

        return usuarioDomain;
    }

    public static UsuarioDomain mapUsuarioAtualiza(UsuarioDomain usuarioAtualiza, UsuarioDomain usuarioDomain){

        EnderecoDomain enderecoAtualiza = usuarioAtualiza.getEndereco();

        if(enderecoAtualiza != null) {
            mapAtualizaComEndereco(usuarioAtualiza, usuarioDomain, enderecoAtualiza);
        } else {
            mapAtualizaSemEndereco(usuarioAtualiza, usuarioDomain);
        }

        return usuarioDomain;
    }

    private static void mapAtualizaSemEndereco(UsuarioDomain usuarioAtualiza, UsuarioDomain usuarioDomain) {
        if(usuarioAtualiza.getCpf() != null)
            usuarioDomain.setCpf(usuarioAtualiza.getCpf());
        if(usuarioAtualiza.getEmail() != null)
            usuarioDomain.setEmail(usuarioAtualiza.getEmail());
        if (usuarioAtualiza.getNomeCompleto() != null)
            usuarioDomain.setNomeCompleto(usuarioAtualiza.getNomeCompleto());
        if (usuarioAtualiza.getApelido() != null)
            usuarioDomain.setApelido(usuarioAtualiza.getApelido());
        if (usuarioAtualiza.getDataNascimento() != null)
            usuarioDomain.setDataNascimento(usuarioAtualiza.getDataNascimento());
        if (usuarioAtualiza.getCelular() != null)
            usuarioDomain.setCelular(usuarioAtualiza.getCelular());
        if (usuarioAtualiza.getGenero() != null)
            usuarioDomain.setGenero(usuarioAtualiza.getGenero());
        usuarioDomain.setDataAtualizacao(LocalDateTime.now());
    }

    private static void mapAtualizaComEndereco (
            UsuarioDomain usuarioRequest,
            UsuarioDomain usuario,
            EnderecoDomain enderecoDomain
    ) {
        mapAtualizaSemEndereco(usuarioRequest, usuario);
        usuario.setEndereco(mapEndereco(enderecoDomain));
    }

    private static EnderecoDomain mapEndereco(EnderecoDomain enderecoDomain){
        if(enderecoDomain == null) return null;
        EnderecoDomain endereco = new EnderecoDomain();

        endereco.setNumero(enderecoDomain.getNumero());
        endereco.setCep(enderecoDomain.getCep());
        endereco.setComplemento(enderecoDomain.getComplemento());

        return endereco;
    }

    public static UsuarioDomain mapToDomain(UsuarioDomain usuarioAtualiza, UsuarioDomain usuarioDomain){
        usuarioDomain.setCpf(usuarioAtualiza.getCpf());
        usuarioDomain.setEmail(usuarioAtualiza.getEmail());
        usuarioDomain.setNomeCompleto(usuarioAtualiza.getNomeCompleto());
        usuarioDomain.setSenha(usuarioAtualiza.getSenha());
        usuarioDomain.setApelido(usuarioAtualiza.getApelido());
        usuarioDomain.setDataNascimento(usuarioAtualiza.getDataNascimento());
        usuarioDomain.setCelular(usuarioAtualiza.getCelular());
        usuarioDomain.setGenero(usuarioAtualiza.getGenero());
        usuarioDomain.setDataAtualizacao(LocalDateTime.now());

        return usuarioDomain;
    }
}
