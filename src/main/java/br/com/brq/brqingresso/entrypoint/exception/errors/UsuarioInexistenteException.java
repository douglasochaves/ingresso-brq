package br.com.brq.brqingresso.entrypoint.exception.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UsuarioInexistenteException extends ErrorsException {

    public UsuarioInexistenteException(String message) {
        super(HttpStatus.NOT_FOUND.value(), "/nao-encontrado", "Nenhum recurso encontrado", message);
    }
}
