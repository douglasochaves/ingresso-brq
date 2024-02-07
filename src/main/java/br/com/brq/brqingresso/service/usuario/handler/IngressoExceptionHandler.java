package br.com.brq.brqingresso.service.usuario.handler;

import br.com.brq.brqingresso.service.usuario.exception.DataNascimentoInvalidaException;
import br.com.brq.brqingresso.service.usuario.exception.InformacaoDuplicadaException;
import br.com.brq.brqingresso.service.usuario.exception.UsuarioInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class IngressoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InformacaoDuplicadaException.class)
    public static ResponseEntity<CorpoMensagemErroResponse> handleInformacaoDuplicada (InformacaoDuplicadaException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse =
                new CorpoMensagemErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(corpoMensagemErroResponse);
    }

    @ExceptionHandler(DataNascimentoInvalidaException.class)
    public static ResponseEntity<CorpoMensagemErroResponse> handleDataNascimentoInvalida (
            DataNascimentoInvalidaException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse =
                new CorpoMensagemErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoMensagemErroResponse);
    }

    @ExceptionHandler(UsuarioInexistenteException.class)
    public static ResponseEntity<CorpoMensagemErroResponse> handleUsuarioInexistente (
            UsuarioInexistenteException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse =
                new CorpoMensagemErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpoMensagemErroResponse);
    }

}
