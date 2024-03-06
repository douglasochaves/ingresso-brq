package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.brqingresso.dataprovider.respositories.UsuarioRepositoryV2;
import br.com.brq.brqingresso.entrypoint.mappers.UsuarioMapV2;
import br.com.brq.brqingresso.usecase.domains.UsuarioV2;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioDataBaseImpl {

    private final UsuarioRepositoryV2 usuarioRepositoryV2;

    public void cadastraUsuario(UsuarioV2 usuario) {
        UsuarioEntity usuarioEntity = UsuarioMapV2.mapUsuarioEntity(usuario);
        usuarioRepositoryV2.save(usuarioEntity);
    }
}
