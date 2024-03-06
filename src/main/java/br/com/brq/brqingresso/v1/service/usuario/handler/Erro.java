package br.com.brq.brqingresso.v1.service.usuario.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Erro {

    private String field;
    private String detail;
}
