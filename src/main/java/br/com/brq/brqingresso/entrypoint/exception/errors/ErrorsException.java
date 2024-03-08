package br.com.brq.brqingresso.entrypoint.exception.errors;

import br.com.brq.brqingresso.common.Helpers;
import lombok.Getter;
import lombok.Setter;

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
