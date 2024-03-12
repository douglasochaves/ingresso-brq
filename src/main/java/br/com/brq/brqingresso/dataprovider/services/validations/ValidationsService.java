package br.com.brq.brqingresso.dataprovider.services.validations;

import br.com.brq.brqingresso.common.Helpers;
import br.com.brq.brqingresso.common.constants.CamposConstants;
import br.com.brq.brqingresso.entrypoint.exception.badrequest.DataNascimentoInvalidaException;
import br.com.brq.brqingresso.entrypoint.exception.badrequest.FormatoCodigoInvalidoException;
import br.com.brq.brqingresso.entrypoint.exception.errors.InformacaoDuplicadaException;
import br.com.brq.brqingresso.entrypoint.exception.errors.InformacaoIncompativelException;
import br.com.brq.brqingresso.entrypoint.exception.errors.TempoExcedidoException;
import br.com.brq.brqingresso.entrypoint.exception.errors.UsuarioInexistenteException;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import br.com.brq.brqingresso.usecase.gateways.UsuarioGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ValidationsService {

    private final UsuarioGateway usuarioGateway;

    public void verificaCadastro(UsuarioDomain usuarioDomain) {
        verificaDataNascimento(usuarioDomain.getDataNascimento());
        verificaDuplicidade(usuarioDomain);
    }

    private void verificaDataNascimento(String dataNascimento) {
        if(dataNascimento == null) return;
        LocalDate dataNascimentoDate = Helpers.convertStringToDate(dataNascimento);
        if(LocalDate.now().isAfter(dataNascimentoDate)) return;
        throw new DataNascimentoInvalidaException(
                "Data de nascimento inválida.",
                CamposConstants.DATA_NASCIMENTO,
                "A Data de nascimento deve ser anterior ao dia atual."
        );
    }

    private void verificaDuplicidade(UsuarioDomain usuario) {
        verificaCpfUnico(usuario.getCpf());
        verificaEmailUnico(usuario.getEmail());
    }

    private void verificaCpfUnico(String cpf) throws InformacaoDuplicadaException {
        Boolean cpfJaExiste = usuarioGateway.existsByCpf(cpf);
        if(cpfJaExiste) {
            throw new InformacaoDuplicadaException(
                    "O usuário não pode ser cadastrado, pois o CPF já se encontra na base de dados."
            );
        }
    }

    private void verificaEmailUnico(String email) throws InformacaoDuplicadaException {
        Boolean emailJaExiste = usuarioGateway.existsByEmail(email);
        if(emailJaExiste) {
            throw new InformacaoDuplicadaException(
                    "O usuário não pode ser cadastrado, pois o E-mail já se encontra na base de dados."
            );
        }
    }

    public UsuarioDomain verificaUsuario(String id) {
        UsuarioDomain usuario = usuarioGateway.findById(id);
        if(usuario == null) throw new UsuarioInexistenteException(
                "O usuario com o id: " + id + " não foi encontrado na base de dados!"
        );
        return usuario;
    }

    public UsuarioDomain verificaAtualizacao(UsuarioDomain usuarioDomain, String id) {
        verificaDataNascimento(usuarioDomain.getDataNascimento());
        UsuarioDomain usuario = verificaUsuario(id);
        return usuario;
    }

    public UsuarioDomain verificaNovaSenha(String codigoSeguranca, String novaSenha, String id) {
        UsuarioDomain usuarioDomain = verificaUsuario(id);
        verificaFormatoCodigoSeguranca(codigoSeguranca);
        verificaCodigoSegurancaUsuario(usuarioDomain, codigoSeguranca);
        verificaNovaSenha(usuarioDomain, novaSenha);
        verificaTempoExcedido(usuarioDomain.getDataHoraCodigoSeguranca());
        return usuarioDomain;
    }

    private void verificaFormatoCodigoSeguranca(String codigo) {
        try{
            UUID.fromString(codigo);
        } catch (IllegalArgumentException e) {
            throw new FormatoCodigoInvalidoException(
                    "Formato do código de segurança inválido.",
                    CamposConstants.CODIGO_SEGURANCA,
                    "O código fornecido não é um UUID."
            );
        }
    }

    private void verificaCodigoSegurancaUsuario(UsuarioDomain usuario, String codigoSeguranca) {
        if(!codigoSeguranca.equals(usuario.getCodigoSeguranca())){
            throw new InformacaoIncompativelException(
                    "O código de segurança informado não corresponde com o deste usuário."
            );
        }
    }

    private void verificaNovaSenha(UsuarioDomain usuario, String novaSenha) {
        if(novaSenha.equals(usuario.getSenha())){
            throw new InformacaoIncompativelException(
                    "A nova senha não pode ser igual a senha atual."
            );
        }
    }

    private void verificaTempoExcedido(String dataHoraCodigoSeguranca) {
        LocalDateTime dataHora = Helpers.convertStringToDateTime(dataHoraCodigoSeguranca);
        String dataHoraAtualString = Helpers.dataHoraAtualFormatada();
        LocalDateTime dataHoraAtual = Helpers.convertStringToDateTime(dataHoraAtualString);

        long diferencaEmMin = java.time.Duration.between(dataHora, dataHoraAtual).toMinutes();
        if(diferencaEmMin > 5){
            throw new TempoExcedidoException(
                    "Tempo máximo de 5 minutos do código de segurança excedido."
            );
        }
    }

    public UsuarioDomain verificaAlteraSenha(String senhaAtual, String id) {
        UsuarioDomain usuarioDomain = verificaUsuario(id);
        verificaSenhaAtual(usuarioDomain, senhaAtual);
        return usuarioDomain;
    }

    private void verificaSenhaAtual(UsuarioDomain usuario, String senhaAtual) {
        if(!senhaAtual.equals(usuario.getSenha())){
            throw new InformacaoIncompativelException(
                    "A senha informada não corresponde com a atual."
            );
        }
    }
}
