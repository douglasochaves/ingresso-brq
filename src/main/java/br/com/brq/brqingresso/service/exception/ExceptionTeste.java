package br.com.brq.brqingresso.service.exception;

import org.springframework.http.ResponseEntity;

public class ExceptionTeste extends Exception {

    public static ResponseEntity<String> teste() {
        return ResponseEntity.status(404).body("O Cpf já está cadastrado!");
    }

}
