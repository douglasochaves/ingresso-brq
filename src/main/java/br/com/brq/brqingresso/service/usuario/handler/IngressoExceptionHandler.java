package br.com.brq.brqingresso.service.usuario.handler;

import br.com.brq.brqingresso.service.usuario.exception.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(CampoNuloException.class)
    public static ResponseEntity<CorpoMensagemErroResponse> handleCampoNulo (
            CampoNuloException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse =
                new CorpoMensagemErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoMensagemErroResponse);
    }

    @ExceptionHandler(FormatoCodigoException.class)
    public static ResponseEntity<CorpoMensagemErroResponse> handleFormatoCodigo (
            FormatoCodigoException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse =
                new CorpoMensagemErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoMensagemErroResponse);
    }

    @ExceptionHandler(InformacaoIncompativelException.class)
    public static ResponseEntity<CorpoMensagemErroResponse> handleInformacaoIncompativel (
            InformacaoIncompativelException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse =
                new CorpoMensagemErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoMensagemErroResponse);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {
            CorpoMensagemErroResponse corpoMensagemErroResponse =
                    new CorpoMensagemErroResponse("Dados de entrada inválidos. Verifique os campos e tente novamente.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoMensagemErroResponse);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
