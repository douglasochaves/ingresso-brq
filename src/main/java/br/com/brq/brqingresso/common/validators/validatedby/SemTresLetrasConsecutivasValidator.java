package br.com.brq.brqingresso.common.validators.validatedby;

import br.com.brq.brqingresso.common.validators.annotations.SemTresLetrasConsecutivas;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SemTresLetrasConsecutivasValidator implements ConstraintValidator<SemTresLetrasConsecutivas, String> {

    @Override
    public void initialize(SemTresLetrasConsecutivas constraintAnnotation){

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String valueUpper = value.toUpperCase();
        for(int i = 2; i < valueUpper.length(); i ++) {
            char a = valueUpper.charAt(i);
            char b = valueUpper.charAt(i-1);
            char c = valueUpper.charAt(i-2);

            if(a == b && b == c) return false;
        }
        return true;
    }

}
