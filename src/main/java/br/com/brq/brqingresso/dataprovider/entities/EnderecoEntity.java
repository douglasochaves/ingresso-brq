package br.com.brq.brqingresso.dataprovider.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;


@Getter
@Setter
@Embeddable
public class EnderecoEntity {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    private String complemento;
}
