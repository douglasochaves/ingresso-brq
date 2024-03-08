package br.com.brq.brqingresso.dataprovider.services.validations;

import br.com.brq.brqingresso.common.Helpers;
import br.com.brq.brqingresso.common.constants.CamposConstants;
import br.com.brq.brqingresso.dataprovider.repositories.UsuarioRepository;
import br.com.brq.brqingresso.entrypoint.exception.badrequest.DataNascimentoInvalidaException;
import br.com.brq.brqingresso.entrypoint.exception.errors.InformacaoDuplicadaException;
import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ValidationsService {

    private final UsuarioRepository usuarioRepository;

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
}
