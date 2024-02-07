package br.com.brq.brqingresso.service.usuario.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformacaoDuplicadaException extends RuntimeException {

    public InformacaoDuplicadaException(String message) {
        super(message);
    }
}
