package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.dataprovider.respositories.UsuarioRepositoryV2;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioEntityMap;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioDataBaseImpl implements UsuarioGateway {

    private final UsuarioRepositoryV2 usuarioRepositoryV2;

    private final UsuarioEntityMap usuarioEntityMap;

    @Override
    public UsuarioDomain save(UsuarioDomain usuarioDomain) {
        UsuarioEntity usuarioEntity = usuarioEntityMap.mapToEntity(usuarioDomain);
        usuarioRepositoryV2.save(usuarioEntity);
        return usuarioDomain;
    }
}
