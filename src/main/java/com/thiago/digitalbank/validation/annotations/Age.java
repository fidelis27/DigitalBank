package com.thiago.digitalbank.validation.annotations;

import com.thiago.digitalbank.validation.validators.AgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {
    String message() default "{com.thiago.digitalbank.validation.annotations.Age}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
