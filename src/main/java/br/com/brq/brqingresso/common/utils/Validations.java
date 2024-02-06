package br.com.brq.brqingresso.common.utils;

import br.com.brq.brqingresso.repositories.UsuarioRepository;
import br.com.brq.brqingresso.service.exception.ExceptionTeste;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class Validations {

    public static Boolean verificaDataNascimento(String dataNascimento) {
        LocalDate dataNascimentoDate = Helpers.convertStringToDate(dataNascimento);
        if(LocalDate.now().isAfter(dataNascimentoDate)) return true;
        return false;
    }


}
