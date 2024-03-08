package br.com.brq.brqingresso.dataprovider.repositories;

import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);
}
