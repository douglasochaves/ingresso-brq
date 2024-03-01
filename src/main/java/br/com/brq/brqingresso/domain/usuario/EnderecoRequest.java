package br.com.brq.brqingresso.domain.usuario;


import br.com.brq.brqingresso.common.validators.annotations.EstadoBrasil;
import br.com.brq.brqingresso.common.validators.annotations.Pais;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EnderecoRequest {

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
