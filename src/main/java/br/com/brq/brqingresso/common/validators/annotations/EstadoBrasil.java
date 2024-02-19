package br.com.brq.brqingresso.common.validators.annotations;

import br.com.brq.brqingresso.common.validators.validatedby.EstadoBrasilValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EstadoBrasilValidator.class)
public @interface EstadoBrasil {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
