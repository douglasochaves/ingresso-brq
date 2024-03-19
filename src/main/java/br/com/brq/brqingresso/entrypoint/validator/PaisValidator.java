package br.com.brq.brqingresso.entrypoint.validator;

import br.com.brq.brqingresso.entrypoint.annotations.Pais;

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
