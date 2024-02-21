package br.com.brq.brqingresso.domain.trocasenha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeraHashTrocaSenhaResponse {

    @JsonProperty("codigo_seguranca")
    private String codigoSeguranca;
}
