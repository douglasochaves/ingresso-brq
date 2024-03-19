package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.usecase.domains.CepDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cepClient", url = "https://viacep.com.br")
public interface CepClient {

    @GetMapping(value = "/ws/{cep}/json/")
    CepDomain buscaCep (@PathVariable String cep);

}
