package br.com.brq.brqingresso.v1.common.validators.validatedby;

import br.com.brq.brqingresso.v1.common.validators.annotations.Pais;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class PaisValidator implements ConstraintValidator<Pais, String> {

    private Set<String> paises = Set.of(
            "BR"
    );

    @Override
    public void initialize(Pais constraintAnnotation){
    }

    @Override
    public boolean isValid(String estado, ConstraintValidatorContext context) {
        if(paises.contains(estado)) return true;
        return false;
    }
}
