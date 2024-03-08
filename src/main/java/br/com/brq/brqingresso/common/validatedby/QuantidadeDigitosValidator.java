package br.com.brq.brqingresso.common.validatedby;

import br.com.brq.brqingresso.common.annotations.QuantidadeDigitos;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuantidadeDigitosValidator implements ConstraintValidator<QuantidadeDigitos, Long> {

    private Integer tamanho;

    @Override
    public void initialize(QuantidadeDigitos constraintAnnotation){
        this.tamanho = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Long celular, ConstraintValidatorContext context) {
        int tamanhoCelular = String.valueOf(celular).length();
        return tamanho == tamanhoCelular;
    }
}
