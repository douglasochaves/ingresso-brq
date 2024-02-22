package br.com.brq.brqingresso.service.cep;

import br.com.brq.brqingresso.domain.cep.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cepClient", url = "https://viacep.com.br")
public interface CepClient {

    @GetMapping(value = "/ws/{cep}/json/")
    CepResponse buscaCep (@PathVariable String cep);

}
