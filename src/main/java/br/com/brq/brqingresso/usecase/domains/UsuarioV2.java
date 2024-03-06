package br.com.brq.brqingresso.usecase.domains;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioV2 {

    private String id;
    private String cpf;
    private String email;
    private String nomeCompleto;
    private String senha;
    private String apelido;
    private String dataNascimento;
    private Long celular;
    private Integer genero;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private String codigoSeguranca;
    private String dataHoraCodigoSeguranca;

    private EnderecoV2 endereco;
}
