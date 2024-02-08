package br.com.brq.brqingresso.domain.usuarioatualiza;

import br.com.brq.brqingresso.domain.usuario.EnderecoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAtualizaResponse {

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
    private EnderecoResponse endereco;
}
