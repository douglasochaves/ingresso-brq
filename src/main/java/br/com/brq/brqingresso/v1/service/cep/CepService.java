package br.com.brq.brqingresso.v1.service.cep;

import br.com.brq.brqingresso.v1.domain.cep.CepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    @Autowired
    private final CepClient cepClient;

    public CepService(CepClient cepClient) {
        this.cepClient = cepClient;
    }

    public CepResponse processCep(String cep) {
        CepResponse cepResponse = cepClient.buscaCep(cep);
        return cepResponse;
    }
}

