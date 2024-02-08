package br.com.brq.brqingresso.service.usuario;

import br.com.brq.brqingresso.common.utils.Validations;
import br.com.brq.brqingresso.domain.usuario.UsuarioRequest;
import br.com.brq.brqingresso.domain.usuario.UsuarioResponse;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.mappers.usuario.UsuarioMap;
import br.com.brq.brqingresso.mappers.usuarioatualiza.UsuarioAtualizaMap;
import br.com.brq.brqingresso.domain.usuarioatualiza.UsuarioResponseAtualiza;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import br.com.brq.brqingresso.service.usuario.exception.InformacaoDuplicadaException;
import br.com.brq.brqingresso.service.usuario.exception.UsuarioInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Usuario usuario = verificaUsuario(id);
        return UsuarioMap.mapUsuarioResponse(usuario);
    }

    public void excluiUsuario(String id) {
        Usuario usuario = verificaUsuario(id);
        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseAtualiza atualizaUsuario(UsuarioRequest usuarioRequest, String id) {
        Validations.verificaDataNascimento(usuarioRequest.getDataNascimento());
        Usuario usuario = verificaUsuario(id);
        Usuario usuarioData = UsuarioAtualizaMap.mapUsuarioAtualiza(usuarioRequest, usuario, id);
        usuarioRepository.save(usuarioData);
        UsuarioResponseAtualiza usuarioResponseAtualiza = UsuarioAtualizaMap.mapUsuarioAtualizaResponse(usuario);
        return usuarioResponseAtualiza;
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

    private Usuario verificaUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null) throw new UsuarioInexistenteException("Usuário não encontrado!");
        return usuario;
    }





}
