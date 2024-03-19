package br.com.brq.brqingresso.usecase.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDomain {

    private String id;
    private String cpf;
    private String email;
    private String nomeCompleto;
    private String senha;
    private String apelido;
    private String dataNascimento;
    private Long celular;
    private String genero;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private String codigoSeguranca;
    private String dataHoraCodigoSeguranca;
    private EnderecoDomain endereco;
}
