package br.com.brq.brqingresso.v1.repositories;

import br.com.brq.brqingresso.v1.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);
    Optional<Usuario> findById(String id);
}
