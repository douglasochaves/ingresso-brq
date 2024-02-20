package br.com.brq.brqingresso.service.usuario.exception.errors;

import br.com.brq.brqingresso.common.utils.Helpers;
import br.com.brq.brqingresso.service.usuario.handler.Erro;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ErrorsException extends RuntimeException{

    private Integer status;
    private String timestamp;
    private String type;
    private String title;
    private String detail;

    public ErrorsException(Integer status, String type, String title, String detail) {
        this.status = status;
        this.timestamp = Helpers.dataHoraAtualFormatada();
        this.type = type;
        this.title = title;
        this.detail = detail;
    }
}
