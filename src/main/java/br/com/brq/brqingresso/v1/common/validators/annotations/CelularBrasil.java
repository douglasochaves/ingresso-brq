package br.com.brq.brqingresso.v1.common.validators.annotations;

import br.com.brq.brqingresso.v1.common.validators.validatedby.CelularBrasilValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CelularBrasilValidator.class)
public @interface CelularBrasil {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
