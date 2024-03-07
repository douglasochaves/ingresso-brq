package br.com.brq.brqingresso.usecase.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDomain {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    private String complemento;

}
