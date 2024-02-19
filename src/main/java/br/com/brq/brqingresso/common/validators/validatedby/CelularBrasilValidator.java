package br.com.brq.brqingresso.common.validators.validatedby;

import br.com.brq.brqingresso.common.validators.annotations.CelularBrasil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class CelularBrasilValidator implements ConstraintValidator<CelularBrasil, Long> {

    private Set<String> ddds = Set.of(
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27",
            "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45",
            "46", "47", "48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65",
            "66", "67", "68", "69", "71", "73", "74", "75", "77", "79", "81", "82", "83",
            "84", "85", "86", "87", "88", "89", "91", "92", "93", "94", "95", "96", "97",
            "98", "99"
    );

    @Override
    public void initialize(CelularBrasil constraintAnnotation){
    }

    @Override
    public boolean isValid(Long celular, ConstraintValidatorContext context) {
        String celularString = String.valueOf(celular);
        String dddCelular = celularString.substring(0, 2);
        char numeroInicial = celularString.charAt(2);
        if(ddds.contains(dddCelular) && '9' == numeroInicial) return true;
        return false;
    }
}
