package com.grigoryev.accountflow.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, String> {

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(dateOfBirth)) {
            return true;
        }
        try {
            LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
