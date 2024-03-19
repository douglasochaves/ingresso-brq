package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.usecase.domains.CepDomain;
import br.com.brq.brqingresso.usecase.gateways.CepGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CepService implements CepGateway {

    @Autowired
    private final CepClient cepClient;

    public CepService(CepClient cepClient) {
        this.cepClient = cepClient;
    }

    @Cacheable("cacheCep")
    @Override
    public CepDomain processCep(String cep) {
        if(cep == null) return null;
        CepDomain cepResponse = cepClient.buscaCep(cep);
        return cepResponse;
    }

    @CacheEvict(value = "cacheCep", allEntries = true)
    @Scheduled(fixedRate = 86400000)
    public void apagaCache() {
    }
}
