package br.com.brq.brqingresso.mocks;

import br.com.brq.brqingresso.v1.domain.cep.CepResponse;

public class CepResponseMock {

    public static CepResponse getCepResponseMock() {
        CepResponse cepResponse = new CepResponse();

        cepResponse.setCep("12234110");
        cepResponse.setLogradouro("Praça Otávio Del Nero");
        cepResponse.setComplemento("casa");
        cepResponse.setBairro("Jardim Imperial");
        cepResponse.setLocalidade("São José dos Campos");
        cepResponse.setUf("Sp");

        return cepResponse;
    }
}
