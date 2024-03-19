package br.com.brq.brqingresso.dataprovider.services;

import br.com.brq.brqingresso.usecase.domains.CepDomain;
import br.com.brq.brqingresso.mocks.CepResponseMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CepServiceTest {

    @Mock
    private CepDomain cepResponse;
    @Mock
    private CepClient cepClient;
    @InjectMocks
    private CepService cepService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.cepResponse = CepResponseMock.getCepResponseMock();
    }

    @Test
    void testProcessCep(){
        when(cepClient.buscaCep("")).thenReturn(cepResponse);
        var result = cepService.processCep("");
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(cepResponse, result)
        );
    }
}