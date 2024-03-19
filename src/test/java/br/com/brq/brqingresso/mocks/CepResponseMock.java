package br.com.brq.brqingresso.mocks;

import br.com.brq.brqingresso.usecase.domains.CepDomain;

public class CepResponseMock {

    public static CepDomain getCepResponseMock() {
        CepDomain cepResponse = new CepDomain();

        cepResponse.setCep("12234110");
        cepResponse.setLogradouro("Praça Otávio Del Nero");
        cepResponse.setComplemento("casa");
        cepResponse.setBairro("Jardim Imperial");
        cepResponse.setLocalidade("São José dos Campos");
        cepResponse.setUf("Sp");

        return cepResponse;
    }
}
