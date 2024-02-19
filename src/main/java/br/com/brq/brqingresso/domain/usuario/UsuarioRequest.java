package br.com.brq.brqingresso.domain.usuario;

import br.com.brq.brqingresso.common.validators.annotations.SemTresLetrasConsecutivas;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequest {

    @Size(min = 11, max = 11)
    @NotBlank
    @NotNull
    @CPF
    private String cpf;

    @Size(max = 50)
    @NotBlank
    @NotNull
    @Email
    private String email;

    @Size(min = 2, max = 100)
    @SemTresLetrasConsecutivas
    @NotBlank
    @NotNull
    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @Size(max = 100)
    @NotBlank
    @NotNull
    private String senha;

    @Size(max = 20)
    private String apelido;

    @NotBlank
    @NotNull
    @JsonProperty("data_nascimento")
    private String dataNascimento;

    @NotNull
    private Long celular;

    @Size(max = 2)
    @NotBlank
    @NotNull
    private String genero;

    private EnderecoRequest endereco;
}
