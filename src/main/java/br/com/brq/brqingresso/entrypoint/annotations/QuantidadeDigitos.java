package br.com.brq.brqingresso.entrypoint.annotations;

import br.com.brq.brqingresso.entrypoint.validator.QuantidadeDigitosValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuantidadeDigitosValidator.class)
public @interface QuantidadeDigitos {
    int value();
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
