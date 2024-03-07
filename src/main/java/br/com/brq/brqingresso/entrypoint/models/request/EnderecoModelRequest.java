package br.com.brq.brqingresso.entrypoint.models.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EnderecoModelRequest {

    @Size(max = 10)
    @NotBlank
    @NotNull
    private String numero;

    @Size(min = 8, max = 8)
    @NotBlank
    @NotNull
    private String cep;

    @Size(max = 50)
    private String complemento;
}
