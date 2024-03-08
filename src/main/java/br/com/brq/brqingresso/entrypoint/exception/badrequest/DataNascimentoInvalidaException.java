package br.com.brq.brqingresso.entrypoint.exception.badrequest;

import br.com.brq.brqingresso.entrypoint.exception.handler.Erro;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataNascimentoInvalidaException extends BadRequestException {

    public DataNascimentoInvalidaException(String message, String field, String detalheErro) {
        super("/data-invalida", "Data de Nascimento Inválida", message, criarListaErros(field, detalheErro));
    }

    private static List<Erro> criarListaErros(String field, String detalheErro) {
        List<Erro> erros = new ArrayList<>();
        erros.add(new Erro(field, detalheErro));
        return erros;
    }
}
