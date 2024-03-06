package br.com.brq.brqingresso.common.utils;

import br.com.brq.brqingresso.v1.common.utils.Validations;
import br.com.brq.brqingresso.v1.service.usuario.exception.badrequest.DataNascimentoInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDataNascimentoInvalida() {
        String dataNascimentoInvalida = "2025-02-26"; // data futura
        assertThrows(DataNascimentoInvalidaException.class, () -> Validations.verificaDataNascimento(dataNascimentoInvalida));
    }
}