package br.com.brq.brqingresso.common.utils;

import java.time.LocalDate;

public class Validations {

    public static Boolean verificaDataNascimento(String dataNascimento) {
        LocalDate dataNascimentoDate = Helpers.convertStringToDate(dataNascimento);
        if(LocalDate.now().isBefore(dataNascimentoDate)) return true;
        return false;
    }
}
