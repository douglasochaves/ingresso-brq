package br.com.brq.brqingresso.usecase.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioListaDomain {

    private String id;
    private String cpf;
    private String email;
    private String nomeCompleto;
}
