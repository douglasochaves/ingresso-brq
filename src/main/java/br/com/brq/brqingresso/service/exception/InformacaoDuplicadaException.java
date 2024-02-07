package br.com.brq.brqingresso.service.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformacaoDuplicadaException extends RuntimeException {

    public InformacaoDuplicadaException(String message) {
        super(message);
    }
}
