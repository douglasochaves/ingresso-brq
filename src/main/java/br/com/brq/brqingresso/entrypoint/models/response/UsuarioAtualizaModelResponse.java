package br.com.brq.brqingresso.entrypoint.models.response;

import br.com.brq.brqingresso.usecase.domains.EnderecoDomain;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAtualizaModelResponse {

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
    @JsonProperty("data_cadastro")
    private String dataCadastro;
    @JsonProperty("data_atualizacao")
    private String dataAtualizacao;
    private EnderecoModelResponse endereco;
}
