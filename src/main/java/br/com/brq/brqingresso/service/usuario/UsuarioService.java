package br.com.brq.brqingresso.service.usuario;

import br.com.brq.brqingresso.common.constants.CamposConstants;
import br.com.brq.brqingresso.common.utils.Helpers;
import br.com.brq.brqingresso.common.utils.Validations;
import br.com.brq.brqingresso.domain.cep.CepResponse;
import br.com.brq.brqingresso.domain.trocasenha.AlteraSenhaRequest;
import br.com.brq.brqingresso.domain.trocasenha.GeraHashTrocaSenhaResponse;
import br.com.brq.brqingresso.domain.trocasenha.NovaSenhaRequest;
import br.com.brq.brqingresso.domain.usuario.EnderecoResponse;
import br.com.brq.brqingresso.domain.usuario.UsuarioRequest;
import br.com.brq.brqingresso.domain.usuario.UsuarioResponse;
import br.com.brq.brqingresso.domain.usuarioatualiza.UsuarioAtualizaResponse;
import br.com.brq.brqingresso.entities.Usuario;
import br.com.brq.brqingresso.mappers.usuario.UsuarioMap;
import br.com.brq.brqingresso.mappers.usuarioatualiza.UsuarioAtualizaMap;
import br.com.brq.brqingresso.repositories.UsuarioRepository;
import br.com.brq.brqingresso.service.cep.CepClient;
import br.com.brq.brqingresso.service.usuario.exception.badrequest.FormatoCodigoException;
import br.com.brq.brqingresso.service.usuario.exception.errors.InformacaoDuplicadaException;
import br.com.brq.brqingresso.service.usuario.exception.errors.InformacaoIncompativelException;
import br.com.brq.brqingresso.service.usuario.exception.errors.UsuarioInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CepClient cepClient;

    public UsuarioResponse processUsuario(UsuarioRequest usuarioRequest) {
            Usuario usuario = UsuarioMap.mapUsuario(usuarioRequest);
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

    public UsuarioAtualizaResponse atualizaUsuario(UsuarioRequest usuarioRequest, String id) {
        Validations.verificaDataNascimento(usuarioRequest.getDataNascimento());
        Usuario usuario = verificaUsuario(id);
        Usuario usuarioData = UsuarioAtualizaMap.mapUsuarioAtualiza(usuarioRequest, usuario);
        usuarioRepository.save(usuarioData);
        UsuarioAtualizaResponse usuarioResponseAtualiza = UsuarioAtualizaMap.mapUsuarioAtualizaResponse(usuario);
        return usuarioResponseAtualiza;
    }

    public GeraHashTrocaSenhaResponse geraHashTrocaSenha(String id) {
        Usuario usuario = verificaUsuario(id);
        GeraHashTrocaSenhaResponse hashTrocaSenhaResponse = new GeraHashTrocaSenhaResponse();
        String hash = UUID.randomUUID().toString();
        hashTrocaSenhaResponse.setCodigoSeguranca(hash);
        usuario.setCodigoSeguranca(hash);
        usuario.setDataHoraCodigoSeguranca(Helpers.dataHoraAtualFormatada());
        usuarioRepository.save(usuario);
        return hashTrocaSenhaResponse;
    }

    public void novaSenha(NovaSenhaRequest novaSenhaRequest, String id) {
        Usuario usuario = verificaUsuario(id);
        verificaUsuarioNovaSenha(novaSenhaRequest, usuario);
    }

    public void alteraSenha(AlteraSenhaRequest alteraSenhaRequest, String id) {
        Usuario usuario = verificaUsuario(id);
        verificaSenhaAtual(usuario, alteraSenhaRequest.getSenhaAtual());
        usuario.setSenha(alteraSenhaRequest.getNovaSenha());
        usuarioRepository.save(usuario);
    }

    private void verificaDuplicidade(Usuario usuario) {
        verificaCpfUnico(usuario.getCpf());
        verificaEmailUnico(usuario.getEmail());
    }


    private void verificaCpfUnico(String cpf) throws InformacaoDuplicadaException {
        if(usuarioRepository.existsByCpf(cpf)) {
            throw new InformacaoDuplicadaException(
                    "O usuário não pode ser cadastrado, pois o CPF já se encontra na base de dados."
            );
        }
    }

    private void verificaEmailUnico(String email) throws InformacaoDuplicadaException {
        if(usuarioRepository.existsByEmail(email)) {
            throw new InformacaoDuplicadaException(
                    "O usuário não pode ser cadastrado, pois o E-mail já se encontra na base de dados."
            );
        }
    }

    private Usuario verificaUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null) throw new UsuarioInexistenteException(
                "O usuario com o id " + id + " não foi encontrado na base de dados!"
        );
        return usuario;
    }

    private void verificaUsuarioNovaSenha(NovaSenhaRequest novaSenhaRequest, Usuario usuario) {
        verificaFormatoCodigoSeguranca(novaSenhaRequest.getCodigoSeguranca());
        verificaCodigoSegurancaUsuario(usuario, novaSenhaRequest.getCodigoSeguranca());
        verificaNovaSenha(usuario, novaSenhaRequest.getNovaSenha());
        verificaTempoExcedido(usuario.getDataHoraCodigoSeguranca());
    }

    private void verificaFormatoCodigoSeguranca(String codigo) {
        try{
            UUID.fromString(codigo);
        } catch (IllegalArgumentException e) {
            throw new FormatoCodigoException(
                "Formato do código de segurança inválido.",
                CamposConstants.CODIGO_SEGURANCA,
                "O código fornecido não é um UUID."
            );
        }
    }

    private void verificaCodigoSegurancaUsuario(Usuario usuario, String codigoSeguranca) {
        if(!codigoSeguranca.equals(usuario.getCodigoSeguranca())){
            throw new InformacaoIncompativelException(
                    "O código de segurança informado não corresponde com o deste usuário."
            );
        }
    }

    private void verificaTempoExcedido(String dataHoraCodigoSeguranca) {
        LocalDateTime dataHora = Helpers.convertStringToDateTime(dataHoraCodigoSeguranca);
        String dataHoraAtualString = Helpers.dataHoraAtualFormatada();
        LocalDateTime dataHoraAtual = Helpers.convertStringToDateTime(dataHoraAtualString);

        long diferencaEmMin = java.time.Duration.between(dataHora, dataHoraAtual).toMinutes();
        if(diferencaEmMin > 5){
            throw new InformacaoIncompativelException(
                    "Tempo máximo de 5 minutos do código de segurança excedido."
            );
        }
    }

    private void verificaNovaSenha(Usuario usuario, String novaSenha) {
        if(novaSenha.equals(usuario.getSenha())){
            throw new InformacaoIncompativelException(
                    "A nova senha não pode ser igual a senha atual."
            );
        }
    }


    private void verificaSenhaAtual(Usuario usuario, String senhaAtual) {
        if(!senhaAtual.equals(usuario.getSenha())){
            throw new InformacaoIncompativelException(
                    "A senha informada não corresponde com a atual."
            );
        }
    }
}
