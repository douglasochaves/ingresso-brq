package br.com.brq.brqingresso.dataprovider.mappers;

import br.com.brq.brqingresso.dataprovider.entities.EnderecoEntity;
import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.usecase.domains.CepDomain;
import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioMapper {

    private static Integer mapGenero(String genero) {
        if(genero == null) return null;
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

    public static UsuarioEntity mapToEntity(UsuarioDomain usuarioDomain){
        UsuarioEntity usuario = new UsuarioEntity();
        EnderecoDomain endereco = usuarioDomain.getEndereco();

        usuario.setId(usuarioDomain.getId());
        usuario.setCpf(usuarioDomain.getCpf());
        usuario.setEmail(usuarioDomain.getEmail());
        usuario.setNomeCompleto(usuarioDomain.getNomeCompleto());
        usuario.setSenha(usuarioDomain.getSenha());
        usuario.setApelido(usuarioDomain.getApelido());
        usuario.setDataNascimento(usuarioDomain.getDataNascimento());
        usuario.setCelular(usuarioDomain.getCelular());
        usuario.setGenero(mapGenero(usuarioDomain.getGenero()));
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setDataAtualizacao(usuarioDomain.getDataAtualizacao());
        usuario.setCodigoSeguranca(usuarioDomain.getCodigoSeguranca());
        usuario.setDataHoraCodigoSeguranca(usuarioDomain.getDataHoraCodigoSeguranca());
        usuario.setEndereco(mapEndereco(endereco));

        return usuario;
    }

    private static EnderecoEntity mapEndereco(EnderecoDomain enderecoDomain){
        EnderecoEntity endereco = new EnderecoEntity();

        endereco.setLogradouro(enderecoDomain.getLogradouro());
        endereco.setNumero(enderecoDomain.getNumero());
        endereco.setBairro(enderecoDomain.getBairro());
        endereco.setCidade(enderecoDomain.getCidade());
        endereco.setEstado(enderecoDomain.getEstado());
        endereco.setCep(enderecoDomain.getCep());
        endereco.setPais("BR");
        endereco.setComplemento(enderecoDomain.getComplemento());

        return endereco;
    }

    public static UsuarioDomain mapCep(UsuarioDomain usuarioDomain, UsuarioEntity usuarioEntity){

        usuarioDomain.setEndereco(mapEnderecoComCep(usuarioEntity.getEndereco()));

        return usuarioDomain;
    }

    private static EnderecoDomain mapEnderecoComCep(EnderecoEntity enderecoEntity){
        EnderecoDomain endereco = new EnderecoDomain();

        endereco.setLogradouro(enderecoEntity.getLogradouro());
        endereco.setNumero(enderecoEntity.getNumero());
        endereco.setBairro(enderecoEntity.getBairro());
        endereco.setCidade(enderecoEntity.getCidade());
        endereco.setEstado(enderecoEntity.getEstado());
        endereco.setPais("BR");
        endereco.setCep(enderecoEntity.getCep());
        endereco.setComplemento(enderecoEntity.getComplemento());

        return endereco;
    }

    public static UsuarioDomain mapToDomain(UsuarioEntity usuarioEntity){
        if(usuarioEntity == null) return null;

        UsuarioDomain usuario = new UsuarioDomain();
        EnderecoEntity endereco = usuarioEntity.getEndereco();

        usuario.setId(usuarioEntity.getId());
        usuario.setCpf(usuarioEntity.getCpf());
        usuario.setEmail(usuarioEntity.getEmail());
        usuario.setNomeCompleto(usuarioEntity.getNomeCompleto());
        usuario.setSenha(usuarioEntity.getSenha());
        usuario.setApelido(usuarioEntity.getApelido());
        usuario.setDataNascimento(usuarioEntity.getDataNascimento());
        usuario.setCelular(usuarioEntity.getCelular());
        usuario.setGenero(mapGenero(usuarioEntity.getGenero()));
        usuario.setDataCadastro(usuarioEntity.getDataCadastro());
        usuario.setCodigoSeguranca(usuarioEntity.getCodigoSeguranca());
        usuario.setDataHoraCodigoSeguranca(usuarioEntity.getDataHoraCodigoSeguranca());
        usuario.setEndereco(mapEnderecoComCep(endereco));

        return usuario;
    }

    private static String mapGenero(Integer genero) {
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

    public static List<UsuarioListaDomain> mapToDomain(List<UsuarioEntity> usuarios) {
        return usuarios.stream().map(usuario ->
                new UsuarioListaDomain(
                        usuario.getId(),
                        usuario.getCpf(),
                        usuario.getEmail(),
                        usuario.getNomeCompleto()
                )).collect(Collectors.toList());
    }

    public static UsuarioEntity mapToEntityAtualiza(UsuarioDomain usuarioDomain){
        UsuarioEntity usuario = new UsuarioEntity();
        EnderecoDomain endereco = usuarioDomain.getEndereco();

        usuario.setId(usuarioDomain.getId());
        usuario.setCpf(usuarioDomain.getCpf());
        usuario.setEmail(usuarioDomain.getEmail());
        usuario.setNomeCompleto(usuarioDomain.getNomeCompleto());
        usuario.setSenha(usuarioDomain.getSenha());
        usuario.setApelido(usuarioDomain.getApelido());
        usuario.setDataNascimento(usuarioDomain.getDataNascimento());
        usuario.setCelular(usuarioDomain.getCelular());
        usuario.setGenero(mapGenero(usuarioDomain.getGenero()));
        usuario.setDataCadastro(usuarioDomain.getDataCadastro());
        usuario.setDataAtualizacao(usuarioDomain.getDataAtualizacao());
        usuario.setEndereco(mapEndereco(endereco));

        return usuario;
    }

    public static UsuarioDomain mapToDomainAtualiza(UsuarioEntity usuarioEntity){
        if(usuarioEntity == null) return null;

        UsuarioDomain usuario = new UsuarioDomain();
        EnderecoEntity endereco = usuarioEntity.getEndereco();

        usuario.setId(usuarioEntity.getId());
        usuario.setCpf(usuarioEntity.getCpf());
        usuario.setEmail(usuarioEntity.getEmail());
        usuario.setNomeCompleto(usuarioEntity.getNomeCompleto());
        usuario.setSenha(usuarioEntity.getSenha());
        usuario.setApelido(usuarioEntity.getApelido());
        usuario.setDataNascimento(usuarioEntity.getDataNascimento());
        usuario.setCelular(usuarioEntity.getCelular());
        usuario.setGenero(mapGenero(usuarioEntity.getGenero()));
        usuario.setDataCadastro(usuarioEntity.getDataCadastro());
        usuario.setDataAtualizacao(usuarioEntity.getDataAtualizacao());
        usuario.setCodigoSeguranca(usuarioEntity.getCodigoSeguranca());
        usuario.setDataHoraCodigoSeguranca(usuarioEntity.getDataHoraCodigoSeguranca());
        usuario.setEndereco(mapEnderecoComCep(endereco));

        return usuario;
    }

}
