package br.com.brq.brqingresso.service.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataNascimentoInvalidaException extends RuntimeException{

    public DataNascimentoInvalidaException(String message) {
        super(message);
    }
}
