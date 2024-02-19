package br.com.brq.brqingresso.common.validators.validatedby;

import br.com.brq.brqingresso.common.validators.annotations.AnoMesDia;
import br.com.brq.brqingresso.common.validators.annotations.SemTresLetrasConsecutivas;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AnoMesDiaValidator implements ConstraintValidator<AnoMesDia, String> {

    @Override
    public void initialize(AnoMesDia constraintAnnotation){
    }

    @Override
    public boolean isValid(String dataNascimento, ConstraintValidatorContext context) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try{
            LocalDate.parse(dataNascimento, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
