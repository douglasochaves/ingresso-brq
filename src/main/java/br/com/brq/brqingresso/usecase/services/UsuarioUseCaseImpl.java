package br.com.brq.brqingresso.usecase.services;

import br.com.brq.brqingresso.dataprovider.services.validations.ValidationsService;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    private final ValidationsService validationsService;

    public UsuarioDomain cadastraUsuario(UsuarioDomain usuario) {
        validationsService.verificaCadastro(usuario);
        usuarioGateway.save(usuario);
        return usuario;
    }

    public List<UsuarioListaDomain> listaUsuarios() {
        List<UsuarioListaDomain> usuarios = usuarioGateway.findAll();
        return usuarios;
    }

    public UsuarioDomain detalhaUsuario(String id) {
        UsuarioDomain usuario = validationsService.verificaUsuario(id);
        return usuario;
    }

    public void excluiUsuario(String id) {
        UsuarioDomain usuario = validationsService.verificaUsuario(id);
        usuarioGateway.delete(usuario);
    }

    @Override
    public UsuarioDomain atualizaUsuario(UsuarioDomain usuarioAtualizado, String id) {
        UsuarioDomain usuario = validationsService.verificaAtualizacao(usuarioAtualizado, id);
        UsuarioDomain usuarioAtualizadoSalvo = usuarioGateway.patch(usuario, usuarioAtualizado);
        return usuarioAtualizadoSalvo;
    }

    @Override
    public UsuarioDomain geraHashTrocaSenha(String id) {
        UsuarioDomain usuarioDomain = validationsService.verificaUsuario(id);
        String hash = UUID.randomUUID().toString();
        UsuarioDomain usuarioCadastrado = usuarioGateway.saveHash(usuarioDomain, hash);
        return usuarioCadastrado;
    }

    @Override
    public void novaSenha(String codigoSeguranca, String novaSenha, String id) {
        UsuarioDomain usuarioDomain = validationsService.verificaUsuarioNovaSenha(codigoSeguranca, novaSenha, id);
    }
}
