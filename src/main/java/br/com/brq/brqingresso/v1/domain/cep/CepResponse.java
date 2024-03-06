package br.com.brq.brqingresso.v1.domain.cep;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
