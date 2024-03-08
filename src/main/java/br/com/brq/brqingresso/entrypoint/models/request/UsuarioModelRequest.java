package br.com.brq.brqingresso.entrypoint.models.request;

import br.com.brq.brqingresso.common.annotations.AnoMesDia;
import br.com.brq.brqingresso.common.annotations.CelularBrasil;
import br.com.brq.brqingresso.common.annotations.QuantidadeDigitos;
import br.com.brq.brqingresso.common.annotations.SemTresLetrasConsecutivas;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class UsuarioModelRequest {

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
    @AnoMesDia
    @JsonProperty("data_nascimento")
    private String dataNascimento;

    @NotNull
    @QuantidadeDigitos(value = 11)
    @CelularBrasil
    private Long celular;

    @Size(max = 2)
    @NotBlank
    @NotNull
    private String genero;

    @Valid
    private EnderecoModelRequest endereco;
}
