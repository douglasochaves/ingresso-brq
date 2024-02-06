package br.com.brq.brqingresso.service;

import br.com.brq.brqingresso.common.utils.Validations;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void processUsuario(Usuario usuario) {
        Validations.verificaDataNascimento(usuario.getDataNascimento());

        usuarioRepository.save(usuario);
    }



}
