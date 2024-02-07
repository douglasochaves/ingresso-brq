package br.com.brq.brqingresso.service.usuario;

import br.com.brq.brqingresso.common.utils.Validations;
import br.com.brq.brqingresso.domain.usuario.UsuarioResponse;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.mappers.usuario.UsuarioMap;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import br.com.brq.brqingresso.service.usuario.exception.InformacaoDuplicadaException;
import br.com.brq.brqingresso.service.usuario.exception.UsuarioInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioResponse processUsuario(Usuario usuario) {
        Validations.verificaDataNascimento(usuario.getDataNascimento());
        verificaDuplicidade(usuario);
        usuarioRepository.save(usuario);
        UsuarioResponse usuarioResponse = UsuarioMap.mapUsuarioResponse(usuario);
        return usuarioResponse;
    }

    public UsuarioResponse detalhaUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null) throw new UsuarioInexistenteException("Usuário não encontrado!");
        return UsuarioMap.mapUsuarioResponse(usuario);
    }

    public void excluiUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null) throw new UsuarioInexistenteException("Usuário não encontrado!");
        usuarioRepository.delete(usuario);
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
