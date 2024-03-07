package br.com.brq.brqingresso.dataprovider.repositories;

import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositoryV2 extends JpaRepository<UsuarioEntity, String> {
}
