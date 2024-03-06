package br.com.brq.brqingresso.v1.entities;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @NotNull
    @Column(name = "id", unique = true)
    private String id;
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "nome_completo")
    private String nomeCompleto;
    private String senha;
    private String apelido;
    @Column(name = "data_nascimento")
    private String dataNascimento;
    private Long celular;
    private Integer genero;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    @Column(name = "codigo_seguranca")
    private String codigoSeguranca;
    @Column(name = "data_hora_codigo_seguranca")
    private String dataHoraCodigoSeguranca;

    @Embedded
    private Endereco endereco;

}
