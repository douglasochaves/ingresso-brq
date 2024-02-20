package br.com.brq.brqingresso.service.usuario.exception;

import br.com.brq.brqingresso.common.utils.Helpers;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UsuarioInexistenteException extends RuntimeException {

    private Integer status = HttpStatus.NOT_FOUND.value();
    private String timestamp = Helpers.dataHoraAtualFormatada();
    private String type = "/nao-encontrado";
    private String title = "Nenhum recurso encontrado";
    private String detail;

    public UsuarioInexistenteException(String message) {
        super(message);
        this.detail = message;
    }
}
