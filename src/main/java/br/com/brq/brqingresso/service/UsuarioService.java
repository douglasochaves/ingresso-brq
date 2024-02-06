package br.com.brq.brqingresso.service;

import br.com.brq.brqingresso.common.utils.Validations;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import br.com.brq.brqingresso.service.exception.ExceptionTeste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public String processUsuario(Usuario usuario) {
        try {
            Validations.verificaDataNascimento(usuario.getDataNascimento());
            verificaCpfUnico(usuario.getCpf());
            usuarioRepository.save(usuario);
            return "";
        } catch (ExceptionTeste e) {
            ExceptionTeste.teste();
            return "teste";
        }
    }

    private void verificaCpfUnico(String cpf) throws ExceptionTeste {
        if(usuarioRepository.existsByCpf(cpf)) {
            throw new ExceptionTeste();
        }
    }



}
