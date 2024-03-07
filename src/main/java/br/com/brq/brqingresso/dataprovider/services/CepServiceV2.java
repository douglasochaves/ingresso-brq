package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.usecase.domains.CepResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepServiceV2 {

    @Autowired
    private final CepClientV2 cepClient;

    public CepServiceV2(CepClientV2 cepClient) {
        this.cepClient = cepClient;
    }

    public CepResponseV2 processCep(String cep) {
        CepResponseV2 cepResponse = cepClient.buscaCep(cep);
        return cepResponse;
    }
}
