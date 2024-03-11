package br.com.brq.brqingresso.entrypoint.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioListaModelResponse {

    private String id;
    private String cpf;
    private String email;
    @JsonProperty("nome_completo")
    private String nomeCompleto;
}
