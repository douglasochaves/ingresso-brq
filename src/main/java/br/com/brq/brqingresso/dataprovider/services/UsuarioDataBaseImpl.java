package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.dataprovider.repositories.UsuarioRepositoryV2;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioDomainMap;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioEntityMap;
import br.com.brq.brqingresso.usecase.domains.CepResponseV2;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioDataBaseImpl implements UsuarioGateway {

    private final UsuarioRepositoryV2 usuarioRepositoryV2;

    private final UsuarioEntityMap usuarioEntityMap;

    private final UsuarioDomainMap usuarioDomainMap;

    private final CepServiceV2 cepServiceV2;

    @Override
    public UsuarioDomain save(UsuarioDomain usuarioDomain) {
        CepResponseV2 cepResponseV2 = cepServiceV2.processCep(usuarioDomain.getEndereco().getCep());
        UsuarioEntity usuarioEntity = usuarioEntityMap.mapToEntity(usuarioDomain, cepResponseV2);
        usuarioRepositoryV2.save(usuarioEntity);
        UsuarioDomain usuarioCadastrado = usuarioDomainMap.mapToDomainComCep(usuarioDomain, usuarioEntity);
        return usuarioCadastrado;
    }
}
