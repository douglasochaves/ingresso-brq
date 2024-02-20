package br.com.brq.brqingresso.service.usuario.exception;

import br.com.brq.brqingresso.common.utils.Helpers;
import br.com.brq.brqingresso.service.usuario.handler.Erro;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InformacaoIncompativelException extends RuntimeException{

    private Integer status = HttpStatus.BAD_REQUEST.value();
    private String timestamp = Helpers.dataHoraAtualFormatada();
    private String type = "/informacao-incompativel";
    private String title = "Informação Incompatível";
    private String detail;
    private List<Erro> erros = new ArrayList<>();
    public InformacaoIncompativelException(String message, String field, String detalheErro) {
        this.detail = message;
        erros.add(new Erro(field, detalheErro));

    }
}
