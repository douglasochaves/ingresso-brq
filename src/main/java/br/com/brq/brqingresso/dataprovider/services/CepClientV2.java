package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.usecase.domains.CepResponseV2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cepClientv2", url = "https://viacep.com.br")
public interface CepClientV2 {

    @GetMapping(value = "/ws/{cep}/json/")
    CepResponseV2 buscaCep (@PathVariable String cep);

}
