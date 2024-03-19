package br.com.brq.brqingresso.entrypoint.mappers;

import br.com.brq.brqingresso.common.Helpers;
import br.com.brq.brqingresso.entrypoint.models.request.EnderecoModelRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioAtualizaModelRequest;
import br.com.brq.brqingresso.entrypoint.models.request.UsuarioModelRequest;
import br.com.brq.brqingresso.entrypoint.models.response.EnderecoModelResponse;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioAtualizaModelResponse;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioListaModelResponse;
import br.com.brq.brqingresso.entrypoint.models.response.UsuarioModelResponse;
import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioMapper {

    public static UsuarioDomain mapToDomain(UsuarioModelRequest usuarioModelRequest){
        UsuarioDomain usuario = new UsuarioDomain();
        EnderecoModelRequest endereco = usuarioModelRequest.getEndereco();

        usuario.setId(UUID.randomUUID().toString());
        usuario.setCpf(usuarioModelRequest.getCpf());
        usuario.setEmail(usuarioModelRequest.getEmail());
        usuario.setNomeCompleto(usuarioModelRequest.getNomeCompleto());
        usuario.setSenha(usuarioModelRequest.getSenha());
        usuario.setApelido(usuarioModelRequest.getApelido());
        usuario.setDataNascimento(usuarioModelRequest.getDataNascimento());
        usuario.setCelular(usuarioModelRequest.getCelular());
        usuario.setGenero(usuarioModelRequest.getGenero());
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setEndereco(mapEndereco(endereco));

        return usuario;
    }

    private static EnderecoDomain mapEndereco(EnderecoModelRequest endereco){
        if(endereco == null) return null;
        EnderecoDomain enderecoMap = new EnderecoDomain();

        enderecoMap.setNumero(endereco.getNumero());
        enderecoMap.setCep(endereco.getCep());
        enderecoMap.setPais("BR");
        enderecoMap.setComplemento(endereco.getComplemento());

        return enderecoMap;
    }

    public static UsuarioModelResponse maptoResponse(UsuarioDomain usuarioDomain){
        UsuarioModelResponse usuario = new UsuarioModelResponse();
        EnderecoDomain endereco = usuarioDomain.getEndereco();

        usuario.setId(usuarioDomain.getId());
        usuario.setCpf(usuarioDomain.getCpf());
        usuario.setEmail(usuarioDomain.getEmail());
        usuario.setNomeCompleto(usuarioDomain.getNomeCompleto());
        usuario.setApelido(usuarioDomain.getApelido());
        usuario.setDataNascimento(usuarioDomain.getDataNascimento());
        usuario.setCelular(usuarioDomain.getCelular());
        usuario.setGenero(usuarioDomain.getGenero());
        usuario.setDataCadastro(Helpers.dataHoraAtualFormatada());
        usuario.setEndereco(mapEnderecoResponse(endereco));

        return usuario;
    }

    private static EnderecoModelResponse mapEnderecoResponse(EnderecoDomain enderecoDomain){
        if(enderecoDomain == null) return null;

        EnderecoModelResponse endereco = new EnderecoModelResponse();

        endereco.setLogradouro(enderecoDomain.getLogradouro());
        endereco.setNumero(enderecoDomain.getNumero());
        endereco.setBairro(enderecoDomain.getBairro());
        endereco.setCidade(enderecoDomain.getCidade());
        endereco.setEstado(enderecoDomain.getEstado());
        endereco.setPais(enderecoDomain.getPais());
        endereco.setCep(enderecoDomain.getCep());
        endereco.setComplemento(enderecoDomain.getComplemento());

        return endereco;
    }

    public static UsuarioDomain mapToDomain(UsuarioAtualizaModelRequest usuarioAtualizaModelRequest){
        UsuarioDomain usuario = new UsuarioDomain();
        EnderecoModelRequest endereco = usuarioAtualizaModelRequest.getEndereco();

        usuario.setCpf(usuarioAtualizaModelRequest.getCpf());
        usuario.setEmail(usuarioAtualizaModelRequest.getEmail());
        usuario.setNomeCompleto(usuarioAtualizaModelRequest.getNomeCompleto());
        usuario.setSenha(usuarioAtualizaModelRequest.getSenha());
        usuario.setApelido(usuarioAtualizaModelRequest.getApelido());
        usuario.setDataNascimento(usuarioAtualizaModelRequest.getDataNascimento());
        usuario.setCelular(usuarioAtualizaModelRequest.getCelular());
        usuario.setGenero(usuarioAtualizaModelRequest.getGenero());
        usuario.setEndereco(mapEndereco(endereco));

        return usuario;
    }

    public static List<UsuarioListaModelResponse> mapToResponse(List<UsuarioListaDomain> usuarios) {
        return usuarios.stream().map(usuario ->
                new UsuarioListaModelResponse(
                        usuario.getId(),
                        usuario.getCpf(),
                        usuario.getEmail(),
                        usuario.getNomeCompleto()
                )).collect(Collectors.toList());
    }

    public static UsuarioAtualizaModelResponse mapToResponse(UsuarioDomain usuarioDomain){
        UsuarioAtualizaModelResponse usuario = new UsuarioAtualizaModelResponse();
        EnderecoDomain endereco = usuarioDomain.getEndereco();

        usuario.setId(usuarioDomain.getId());
        usuario.setCpf(usuarioDomain.getCpf());
        usuario.setEmail(usuarioDomain.getEmail());
        usuario.setNomeCompleto(usuarioDomain.getNomeCompleto());
        usuario.setApelido(usuarioDomain.getApelido());
        usuario.setDataNascimento(usuarioDomain.getDataNascimento());
        usuario.setCelular(usuarioDomain.getCelular());
        usuario.setGenero(usuarioDomain.getGenero());
        usuario.setDataCadastro(Helpers.dataHoraFormatada(usuarioDomain.getDataCadastro()));
        usuario.setDataAtualizacao(Helpers.dataHoraAtualFormatada());
        usuario.setEndereco(mapEnderecoResponse(endereco));

        return usuario;
    }

    public static UsuarioDomain mapUsuarioAtualiza (UsuarioModelRequest usuarioModelRequest){

        UsuarioDomain usuario = new UsuarioDomain();
        EnderecoModelRequest endereco = usuarioModelRequest.getEndereco();

        if(endereco != null) {
            mapAtualizaComEndereco(usuarioModelRequest, usuario, endereco);
        } else {
            mapAtualizaSemEndereco(usuarioModelRequest, usuario);
        }

        return usuario;
    }

    private static void mapAtualizaSemEndereco(UsuarioModelRequest usuarioModelRequest, UsuarioDomain usuarioDomain) {
        if(usuarioModelRequest.getCpf() != null)
            usuarioDomain.setCpf(usuarioModelRequest.getCpf());
        if(usuarioModelRequest.getEmail() != null)
            usuarioDomain.setEmail(usuarioModelRequest.getEmail());
        if (usuarioModelRequest.getNomeCompleto() != null)
            usuarioDomain.setNomeCompleto(usuarioModelRequest.getNomeCompleto());
        if (usuarioModelRequest.getApelido() != null)
            usuarioDomain.setApelido(usuarioModelRequest.getApelido());
        if (usuarioModelRequest.getDataNascimento() != null)
            usuarioDomain.setDataNascimento(usuarioModelRequest.getDataNascimento());
        if (usuarioModelRequest.getCelular() != null)
            usuarioDomain.setCelular(usuarioModelRequest.getCelular());
        if (usuarioModelRequest.getGenero() != null)
            usuarioDomain.setGenero(usuarioModelRequest.getGenero());
        usuarioDomain.setDataAtualizacao(LocalDateTime.now());
    }

    private static void mapAtualizaComEndereco (
            UsuarioModelRequest usuarioModelRequest,
            UsuarioDomain usuarioDomain,
            EnderecoModelRequest enderecoRequest
    ) {
        mapAtualizaSemEndereco(usuarioModelRequest, usuarioDomain);
        usuarioDomain.setEndereco(mapEndereco(enderecoRequest));
    }
}
