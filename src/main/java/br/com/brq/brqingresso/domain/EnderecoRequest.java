package br.com.brq.brqingresso.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
}
