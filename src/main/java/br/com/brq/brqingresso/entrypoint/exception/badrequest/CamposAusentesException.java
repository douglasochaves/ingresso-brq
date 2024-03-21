package br.com.brq.brqingresso.entrypoint.exception.badrequest;

import br.com.brq.brqingresso.entrypoint.exception.handler.Erro;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CamposAusentesException extends BadRequestException {

    public CamposAusentesException(String message, String field, String detalheErro) {
        super("/campoausente", "Há campos ausentes no corpo da requisição.", message, criarListaErros(field, detalheErro));
    }

    private static List<Erro> criarListaErros(String field, String detalheErro) {
        List<Erro> erros = new ArrayList<>();
        erros.add(new Erro(field, detalheErro));
        return erros;
    }
}
