package br.com.brq.brqingresso.domain.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;


@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequest {

    private String cpf;
    private String email;
    @JsonProperty("nome_completo")
    private String nomeCompleto;
    private String senha;
    private String apelido;
    @JsonProperty("data_nascimento")
    private String dataNascimento;
    private Long celular;
    private String genero;
    private EnderecoRequest endereco;
}
