package br.com.brq.brqingresso.domain.usuario;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class EnderecoRequest {

    @Size(max = 100)
    private String logradouro;

    @Size(max = 10)
    private String numero;

    @Size(max = 20)
    private String bairro;

    @Size(max = 50)
    private String cidade;

    @Size(max = 2)
    private String estado;

    @Size(max = 2)
    private String pais;

    @Size(max = 8)
    private String cep;

    private String complemento;
}
