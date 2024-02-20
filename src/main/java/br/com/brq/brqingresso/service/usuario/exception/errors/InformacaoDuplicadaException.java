package br.com.brq.brqingresso.service.usuario.exception.errors;

import br.com.brq.brqingresso.common.utils.Helpers;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InformacaoDuplicadaException extends ErrorsException {

    public InformacaoDuplicadaException(String message) {
        super(HttpStatus.CONFLICT.value(), "/em-conflito", "Requisição em Conflito", message);
    }
}
