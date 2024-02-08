package br.com.brq.brqingresso.service.usuario.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampoNuloException extends RuntimeException {

    public CampoNuloException(String message) {
        super(message);
    }
}
