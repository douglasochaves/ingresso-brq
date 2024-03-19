package br.com.brq.brqingresso.usecase.gateways;

import br.com.brq.brqingresso.usecase.domains.CepDomain;

public interface CepGateway {

    CepDomain processCep(String cep);
}
