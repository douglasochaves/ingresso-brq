package br.com.brq.brqingresso.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "email", unique = true)
    private String email;

    private String nomeCompleto;
    private String senha;
    private String apelido;
    private String dataNascimento;
    private Long celular;
    private String genero;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    @Embedded
    private Endereco endereco;

}
