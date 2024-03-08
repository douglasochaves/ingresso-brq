package br.com.brq.brqingresso.entrypoint.exception.badrequest;

import br.com.brq.brqingresso.common.Helpers;
import br.com.brq.brqingresso.entrypoint.exception.handler.Erro;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class BadRequestException extends RuntimeException {

    private Integer status;
    private String timestamp;
    private String type;
    private String title;
    private String detail;
    private List<Erro> erros;
    public BadRequestException(String type, String title, String detail, List<Erro> erros) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.timestamp = Helpers.dataHoraAtualFormatada();
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.erros = erros;
    }
}
