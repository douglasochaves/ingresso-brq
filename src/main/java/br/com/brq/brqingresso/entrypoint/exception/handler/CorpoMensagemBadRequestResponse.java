package br.com.brq.brqingresso.entrypoint.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude
@AllArgsConstructor
public class CorpoMensagemBadRequestResponse {

    private Integer status;
    private String timestamp;
    private String type;
    private String title;
    private String detail;
    private List<Erro> erros;
}
