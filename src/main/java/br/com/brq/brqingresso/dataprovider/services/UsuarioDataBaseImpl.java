package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.dataprovider.repositories.UsuarioRepository;
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
}
