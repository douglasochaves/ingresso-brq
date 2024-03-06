package br.com.brq.brqingresso.v1.service.cep;

import br.com.brq.brqingresso.v1.domain.cep.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cepClient", url = "https://viacep.com.br")
public interface CepClient {

    @GetMapping(value = "/ws/{cep}/json/")
    CepResponse buscaCep (@PathVariable String cep);

}
