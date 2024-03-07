package br.com.brq.brqingresso.usecase.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepResponseV2 {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
