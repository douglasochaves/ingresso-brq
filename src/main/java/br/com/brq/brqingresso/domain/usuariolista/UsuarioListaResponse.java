package br.com.brq.brqingresso.domain.usuariolista;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioListaResponse {

    private String id;
    private String cpf;
    private String email;
    @JsonProperty("nome_completo")
    private String nomeCompleto;
}
