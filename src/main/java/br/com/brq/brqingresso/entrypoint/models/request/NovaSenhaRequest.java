package br.com.brq.brqingresso.entrypoint.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovaSenhaRequest {

    @JsonProperty("codigo_seguranca")
    private String codigoSeguranca;
    @JsonProperty("nova_senha")
    private String novaSenha;
}
