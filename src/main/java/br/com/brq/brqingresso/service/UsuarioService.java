package br.com.brq.brqingresso.service;

import br.com.brq.brqingresso.common.utils.Validations;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import br.com.brq.brqingresso.service.exception.InformacaoDuplicadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public String processUsuario(Usuario usuario) {
            Validations.verificaDataNascimento(usuario.getDataNascimento());
            verificaDuplicidade(usuario);
            usuarioRepository.save(usuario);
            return "";
    }

    private void verificaDuplicidade(Usuario usuario) {
        verificaCpfUnico(usuario.getCpf());
        verificaEmailUnico(usuario.getEmail());
    }


    private void verificaCpfUnico(String cpf) throws InformacaoDuplicadaException {
        if(usuarioRepository.existsByCpf(cpf)) {
            throw new InformacaoDuplicadaException("O CPF já está cadastrado!");
        }
    }

    private void verificaEmailUnico(String email) throws InformacaoDuplicadaException {
        if(usuarioRepository.existsByEmail(email)) {
            throw new InformacaoDuplicadaException("O Email já está cadastrado!");
        }
    }





}
