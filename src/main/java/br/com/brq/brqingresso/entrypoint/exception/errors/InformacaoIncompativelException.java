package br.com.brq.brqingresso.entrypoint.exception.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InformacaoIncompativelException extends ErrorsException {

    public InformacaoIncompativelException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY.value(), "/nao-processada", "A Requisicao nao pode ser processada", message);
    }
}
