package br.com.brq.brqingresso.entrypoint.exception.handler;

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
