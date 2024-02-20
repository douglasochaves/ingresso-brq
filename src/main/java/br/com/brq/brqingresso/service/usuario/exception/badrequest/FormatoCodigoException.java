package br.com.brq.brqingresso.service.usuario.exception.badrequest;

import br.com.brq.brqingresso.common.utils.Helpers;
import br.com.brq.brqingresso.service.usuario.handler.Erro;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FormatoCodigoException extends BadRequestException{

    public FormatoCodigoException(String message, String field, String detalheErro) {
        super("/formato-invalido", "Formato do Código Inválido", message, criarListaErros(field, detalheErro));
    }

    private static List<Erro> criarListaErros(String field, String detalheErro) {
        List<Erro> erros = new ArrayList<>();
        erros.add(new Erro(field, detalheErro));
        return erros;
    }

}
