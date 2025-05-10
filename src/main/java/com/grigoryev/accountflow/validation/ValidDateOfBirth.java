package com.grigoryev.accountflow.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateOfBirthValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateOfBirth {

    String message() default "Date of birth must be in format dd.MM.yyyy (example: 01.05.1993)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}