package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.entrypoint.models.response.CepResponse;
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
        if(cep == null) return null;
        CepResponse cepResponse = cepClient.buscaCep(cep);
        return cepResponse;
    }
}
