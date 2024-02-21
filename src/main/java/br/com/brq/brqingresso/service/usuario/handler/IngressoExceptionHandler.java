package br.com.brq.brqingresso.service.usuario.handler;

import br.com.brq.brqingresso.common.utils.Helpers;
import br.com.brq.brqingresso.service.usuario.exception.badrequest.BadRequestException;
import br.com.brq.brqingresso.service.usuario.exception.badrequest.DataNascimentoInvalidaException;
import br.com.brq.brqingresso.service.usuario.exception.badrequest.FormatoCodigoException;
import br.com.brq.brqingresso.service.usuario.exception.errors.InformacaoIncompativelException;
import br.com.brq.brqingresso.service.usuario.exception.errors.ErrorsException;
import br.com.brq.brqingresso.service.usuario.exception.errors.InformacaoDuplicadaException;
import br.com.brq.brqingresso.service.usuario.exception.errors.UsuarioInexistenteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class IngressoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InformacaoDuplicadaException.class)
    public static ResponseEntity<CorpoMensagemErroResponse> handleInformacaoDuplicada (InformacaoDuplicadaException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse = getCorpoMensagemErroResponse(e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(corpoMensagemErroResponse);
    }

    @ExceptionHandler(UsuarioInexistenteException.class)
    public static ResponseEntity<CorpoMensagemErroResponse> handleUsuarioInexistente (
            UsuarioInexistenteException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse = getCorpoMensagemErroResponse(e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpoMensagemErroResponse);
    }

    @ExceptionHandler(InformacaoIncompativelException.class)
    public static ResponseEntity<CorpoMensagemErroResponse> handleInformacaoIncompativel (
            InformacaoIncompativelException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse = getCorpoMensagemErroResponse(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoMensagemErroResponse);
    }

    private static CorpoMensagemErroResponse getCorpoMensagemErroResponse(ErrorsException e) {
        CorpoMensagemErroResponse corpoMensagemErroResponse =
                new CorpoMensagemErroResponse(
                        e.getStatus(),
                        e.getTimestamp(),
                        e.getType(),
                        e.getTitle(),
                        e.getDetail()
                );
        return corpoMensagemErroResponse;
    }

    @ExceptionHandler(DataNascimentoInvalidaException.class)
    public static ResponseEntity<CorpoMensagemBadRequestResponse> handleDataNascimentoInvalida (
            DataNascimentoInvalidaException e) {
        CorpoMensagemBadRequestResponse corpoMensagemBadRequestResponse = getCorpoMensagemBadRequestResponse(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoMensagemBadRequestResponse);
    }
    @ExceptionHandler(FormatoCodigoException.class)
    public static ResponseEntity<CorpoMensagemBadRequestResponse> handleFormatoCodigo (
            FormatoCodigoException e) {
        CorpoMensagemBadRequestResponse corpoMensagemBadRequestResponse = getCorpoMensagemBadRequestResponse(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoMensagemBadRequestResponse);
    }

    private static CorpoMensagemBadRequestResponse getCorpoMensagemBadRequestResponse(BadRequestException e) {
        CorpoMensagemBadRequestResponse corpoMensagemBadRequestResponse =
                new CorpoMensagemBadRequestResponse(
                        e.getStatus(),
                        e.getTimestamp(),
                        e.getType(),
                        e.getTitle(),
                        e.getDetail(),
                        e.getErros()
                );
        return corpoMensagemBadRequestResponse;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            List<Erro> erros = new ArrayList<>();

            for(FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
                String campo = error.getField();
                String codigoErro = error.getCode();
                String detalhe = processError(codigoErro, campo);
                erros.add(new Erro(campo, detalhe));
            }

            CorpoMensagemBadRequestResponse corpoMensagemBadRequestResponse =
                    new CorpoMensagemBadRequestResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            Helpers.dataHoraAtualFormatada(),
                            "/campo-invalido",
                            "Campo Inválido",
                            "Dados de entrada inválidos. Verifique os campos e tente novamente.",
                            erros
                    );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoMensagemBadRequestResponse);
        }
        return super.handleExceptionInternal(e, body, headers, status, request);
    }

    private String processError(String codigoErro, String campo) {
        String detalhe = "";

        if("NotNull".equals(codigoErro)) {
            detalhe = "O campo " + campo + " é obrigatório.";
        } else if ("Size".equals(codigoErro)) {
            detalhe = "O campo " + campo + " está fora dos limites de tamanho.";
        } else if("NotBlank".equals(codigoErro)) {
            detalhe = "O campo " + campo + " deve ser preenchido.";
        } else if("CPF".equals(codigoErro)) {
            detalhe = "O CPF é inválido.";
        } else if("Email".equals(codigoErro)) {
            detalhe = "O E-mail está no formato inválido.";
        } else if("SemTresLetrasConsecutivas".equals(codigoErro)) {
            detalhe = "O nome não pode ter três letras iguais consecutivas.";
        } else if("AnoMesDia".equals(codigoErro)) {
            detalhe = "A data deve estar no formato yyyy-MM-dd.";
        } else if("CelularBrasil".equals(codigoErro) || "QuantidadeDigitos".equals(codigoErro)) {
            detalhe = "O formato do número de celular válido é: xx9xxxxxxxx.";
        } else {
            detalhe = "Erro de validação não identificado.";
        }

        return detalhe;
    }

}
