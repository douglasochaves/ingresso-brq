package br.com.brq.brqingresso.service.usuario.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude
@AllArgsConstructor
public class CorpoMensagemErroResponse {

    private String mensagem;
}
