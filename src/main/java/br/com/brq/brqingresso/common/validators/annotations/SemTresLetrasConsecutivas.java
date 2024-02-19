package br.com.brq.brqingresso.common.validators.annotations;

import br.com.brq.brqingresso.common.validators.validatedby.SemTresLetrasConsecutivasValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SemTresLetrasConsecutivasValidator.class)
public @interface SemTresLetrasConsecutivas {
    String message() default ""; // Define a mensagem padrão como uma string vazia
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
