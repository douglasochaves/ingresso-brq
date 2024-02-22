package br.com.brq.brqingresso.domain.usuarioatualiza;

import br.com.brq.brqingresso.common.validators.annotations.AnoMesDia;
import br.com.brq.brqingresso.common.validators.annotations.CelularBrasil;
import br.com.brq.brqingresso.common.validators.annotations.QuantidadeDigitos;
import br.com.brq.brqingresso.common.validators.annotations.SemTresLetrasConsecutivas;
import br.com.brq.brqingresso.domain.usuario.EnderecoRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class UsuarioAtualizaRequest {

    @Size(min = 11, max = 11)
    @CPF
    private String cpf;

    @Size(max = 50)
    @Email
    private String email;

    @Size(min = 2, max = 100)
    @SemTresLetrasConsecutivas
    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @Size(max = 100)
    private String senha;

    @Size(max = 20)
    private String apelido;

    @AnoMesDia
    @JsonProperty("data_nascimento")
    private String dataNascimento;

    @QuantidadeDigitos(value = 11)
    @CelularBrasil
    private Long celular;

    @Size(max = 2)
    private String genero;

    @Valid
    private EnderecoRequest endereco;
}
