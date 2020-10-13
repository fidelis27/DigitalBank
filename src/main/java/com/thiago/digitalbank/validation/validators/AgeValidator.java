package com.thiago.digitalbank.validation.validators;

import com.thiago.digitalbank.validation.annotations.Age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;


public class AgeValidator implements ConstraintValidator<Age,LocalDate>{
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate now = LocalDate.now();
        return Period.between(localDate, now).toTotalMonths() >= 216;
    }
}
