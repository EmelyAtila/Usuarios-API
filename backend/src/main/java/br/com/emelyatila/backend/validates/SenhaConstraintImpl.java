package br.com.emelyatila.backend.validates;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class SenhaConstraintImpl implements ConstraintValidator<SenhaConstraint, String> {
    private static final String SENHA_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-\\[{}\\]:;',?/*~$^+=<>]).{5,20}$";

    @Override
    public void initialize(SenhaConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String senha, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(SENHA_PATTERN);

        if (senha == null || senha.trim().isEmpty() || senha.contains(" ")) {
            return false;
        } else if (!pattern.matcher(senha).matches()) {
            return false;
        }
        return true;
    }
}
