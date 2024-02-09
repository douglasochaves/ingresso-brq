package br.com.brq.brqingresso.domain.trocasenha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlteraSenhaRequest {

    @JsonProperty("senha_atual")
    private String senhaAtual;
    @JsonProperty("nova_senha")
    private String novaSenha;

}
