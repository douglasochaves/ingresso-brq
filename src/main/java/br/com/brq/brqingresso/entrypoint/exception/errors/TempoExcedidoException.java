package br.com.brq.brqingresso.entrypoint.exception.errors;

import org.springframework.http.HttpStatus;

public class TempoExcedidoException extends ErrorsException {

    public TempoExcedidoException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY.value(), "/tempo-excedido", "O tempo máximo de resposta foi excedido", message);
    }
}
