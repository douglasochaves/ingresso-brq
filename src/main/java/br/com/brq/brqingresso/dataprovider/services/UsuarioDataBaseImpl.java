package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.common.Helpers;
import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.dataprovider.mappers.UsuarioMapper;
import br.com.brq.brqingresso.dataprovider.repositories.UsuarioRepository;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioDataBaseImpl implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;
    private final CepService cepService;

    @Override
    public UsuarioDomain save(UsuarioDomain usuarioDomain) {
        UsuarioEntity usuarioEntity = UsuarioMapper.mapToEntity(usuarioDomain);
        usuarioRepository.save(usuarioEntity);
        return UsuarioMapper.mapCep(usuarioDomain, usuarioEntity);
    }

    @Override
    public List<UsuarioListaDomain> findAll() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return UsuarioMapper.mapToDomain(usuarios);
    }

    @Override
    public Boolean existsByCpf(String cpf) {
        return usuarioRepository.existsByCpf(cpf);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public UsuarioDomain findById(String id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElse(null);
        return UsuarioMapper.mapToDomain(usuario);
    }

    @Override
    public void delete(UsuarioDomain usuarioDomain) {
        UsuarioEntity usuarioEntity = UsuarioMapper.mapToEntity(usuarioDomain);
        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    public UsuarioDomain patch(UsuarioDomain usuarioAtualizado) {
        UsuarioEntity usuarioEntity = UsuarioMapper.mapToEntity(usuarioAtualizado);
        usuarioRepository.save(usuarioEntity);
        UsuarioDomain usuarioDomainAtualizado = UsuarioMapper.mapToDomain(usuarioEntity);
        return usuarioDomainAtualizado;
    }

    @Override
    public UsuarioDomain saveHash(UsuarioDomain usuarioDomain, String hash) {
        usuarioDomain.setCodigoSeguranca(hash);
        usuarioDomain.setDataHoraCodigoSeguranca(Helpers.dataHoraAtualFormatada());
        UsuarioEntity usuarioEntity = UsuarioMapper.mapToEntity(usuarioDomain);
        usuarioRepository.save(usuarioEntity);
        UsuarioDomain usuarioComHash = UsuarioMapper.mapToDomain(usuarioEntity);
        return usuarioComHash;
    }

    @Override
    public void putSenha(UsuarioDomain usuarioDomain, String novaSenha) {
        usuarioDomain.setSenha(novaSenha);
        UsuarioEntity usuarioEntity = UsuarioMapper.mapToEntity(usuarioDomain);
        usuarioRepository.save(usuarioEntity);
    }
}
