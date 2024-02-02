package br.com.brq.brqingresso.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    private String id;
    private String cpf;
    private String email;
    @JsonProperty("nome_completo")
    private String nomeCompleto;
    private String apelido;
    @JsonProperty("data_nascimento")
    private String dataNascimento;
    private Long celular;
    private String genero;
    private String dataCadastro;
    private EnderecoResponse endereco;

}
