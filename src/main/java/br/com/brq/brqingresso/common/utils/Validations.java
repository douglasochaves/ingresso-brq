package br.com.brq.brqingresso.common.utils;

import br.com.brq.brqingresso.common.constants.CamposConstants;
import br.com.brq.brqingresso.service.usuario.exception.badrequest.DataNascimentoInvalidaException;

import java.time.LocalDate;

public class Validations {

    public static void verificaDataNascimento(String dataNascimento) {
        if(dataNascimento == null) return;
        LocalDate dataNascimentoDate = Helpers.convertStringToDate(dataNascimento);
        if(LocalDate.now().isAfter(dataNascimentoDate)) return;
        throw new DataNascimentoInvalidaException(
                "Data de nascimento inválida.",
                CamposConstants.DATA_NASCIMENTO,
                "A Data de nascimento deve ser anterior ao dia atual."
        );
    }


}
