package br.com.brq.brqingresso.common.utils;

import br.com.brq.brqingresso.service.exception.DataNascimentoInvalidaException;

import java.time.LocalDate;

public class Validations {

    public static Boolean verificaDataNascimento(String dataNascimento) {
        LocalDate dataNascimentoDate = Helpers.convertStringToDate(dataNascimento);
        if(LocalDate.now().isAfter(dataNascimentoDate)) return true;
        throw new DataNascimentoInvalidaException("A Data de nascimento deve ser anterior ao dia atual");
    }


}
