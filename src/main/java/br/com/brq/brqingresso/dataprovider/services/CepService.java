package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.entrypoint.models.response.CepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    @Autowired
    private final CepClient cepClient;

    public CepService(CepClient cepClient) {
        this.cepClient = cepClient;
    }

    @Cacheable("cacheCep")
    public CepResponse processCep(String cep) {
        if(cep == null) return null;
        CepResponse cepResponse = cepClient.buscaCep(cep);
        return cepResponse;
    }

    @CacheEvict(value = "cacheCep", allEntries = true)
    @Scheduled(fixedRate = 86400000)
    public void apagaCache() {
    }
}
