package com.springboot.springBootDemo.validations;

import java.util.Arrays;
import java.util.List;

import com.springboot.springBootDemo.annotations.PasswordValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String>{

	
	List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator passwordValidator) {
        weakPasswords = Arrays.asList("12345", "password", "qwerty");
    }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && (!weakPasswords.contains(value));
	}

}
