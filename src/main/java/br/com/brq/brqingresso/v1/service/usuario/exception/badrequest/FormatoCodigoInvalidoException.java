package br.com.brq.brqingresso.v1.service.usuario.exception.badrequest;

import br.com.brq.brqingresso.v1.service.usuario.handler.Erro;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FormatoCodigoInvalidoException extends BadRequestException{

    public FormatoCodigoInvalidoException(String message, String field, String detalheErro) {
        super("/formato-invalido", "Formato do Código Inválido", message, criarListaErros(field, detalheErro));
    }

    private static List<Erro> criarListaErros(String field, String detalheErro) {
        List<Erro> erros = new ArrayList<>();
        erros.add(new Erro(field, detalheErro));
        return erros;
    }

}
