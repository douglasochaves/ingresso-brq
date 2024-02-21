package br.com.brq.brqingresso.service.usuario.exception.errors;

import br.com.brq.brqingresso.common.utils.Helpers;
import br.com.brq.brqingresso.service.usuario.exception.badrequest.BadRequestException;
import br.com.brq.brqingresso.service.usuario.handler.Erro;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InformacaoIncompativelException extends ErrorsException {

    public InformacaoIncompativelException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY.value(), "/nao-processada", "A Requisicao nao pode ser processada", message);
    }
}
