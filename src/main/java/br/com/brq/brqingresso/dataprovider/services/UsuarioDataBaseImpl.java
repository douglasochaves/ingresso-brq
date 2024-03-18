package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.common.Helpers;
import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.dataprovider.repositories.UsuarioRepository;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioAtualizaMap;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioDomainMap;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioEntityMap;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioListaMap;
import br.com.brq.brqingresso.entrypoint.models.response.CepResponse;
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
    private final UsuarioEntityMap usuarioEntityMap;
    private final UsuarioDomainMap usuarioDomainMap;
    private final UsuarioListaMap usuarioListaMap;
    private final UsuarioAtualizaMap usuarioAtualizaMap;
    private final CepService cepService;

    @Override
    public UsuarioDomain save(UsuarioDomain usuarioDomain) {
        CepResponse cepResponse = cepService.processCep(usuarioDomain.getEndereco().getCep());
        UsuarioEntity usuarioEntity = usuarioEntityMap.mapToEntity(usuarioDomain, cepResponse);
        usuarioRepository.save(usuarioEntity);
        UsuarioDomain usuarioCadastrado = usuarioDomainMap.mapToDomainComCep(usuarioDomain, usuarioEntity);
        return usuarioCadastrado;
    }

    @Override
    public List<UsuarioListaDomain> findAll() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        List<UsuarioListaDomain> usuariosDomain = usuarioListaMap.mapToUsuarioListaDomain(usuarios);
        return usuariosDomain;
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
        UsuarioDomain usuarioDomain = usuarioDomainMap.mapToDomain(usuario);
        return usuarioDomain;
    }

    @Override
    public void delete(UsuarioDomain usuarioDomain) {
        UsuarioEntity usuarioEntity = usuarioEntityMap.mapToEntity(usuarioDomain);
        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    public UsuarioDomain patch(UsuarioDomain usuario, UsuarioDomain usuarioAtualizado) {
        CepResponse cepResponse = cepService.processCep(usuarioAtualizado.getEndereco().getCep());
        UsuarioEntity usuarioEntity = usuarioEntityMap.mapToEntity(usuario, cepResponse);
        UsuarioEntity usuarioEntityAtualizado =
                usuarioAtualizaMap.mapUsuarioAtualiza(usuarioAtualizado, usuarioEntity, cepResponse);
        usuarioRepository.save(usuarioEntityAtualizado);
        UsuarioDomain usuarioDomainAtualizado = usuarioDomainMap.mapToDomain(usuarioEntity);
        return usuarioDomainAtualizado;
    }

    @Override
    public UsuarioDomain saveHash(UsuarioDomain usuarioDomain, String hash) {
        usuarioDomain.setCodigoSeguranca(hash);
        usuarioDomain.setDataHoraCodigoSeguranca(Helpers.dataHoraAtualFormatada());
        UsuarioEntity usuarioEntity = usuarioEntityMap.mapToEntity(usuarioDomain);
        usuarioRepository.save(usuarioEntity);
        UsuarioDomain usuarioComHash = usuarioDomainMap.mapToDomain(usuarioEntity);
        return usuarioComHash;
    }

    @Override
    public void putSenha(UsuarioDomain usuarioDomain, String novaSenha) {
        usuarioDomain.setSenha(novaSenha);
        UsuarioEntity usuarioEntity = usuarioEntityMap.mapToEntity(usuarioDomain);
        usuarioRepository.save(usuarioEntity);
    }
}
