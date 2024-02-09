package br.com.brq.brqingresso.service.usuario.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormatoCodigoException extends RuntimeException{

    public FormatoCodigoException(String message) {
        super(message);
    }

}
