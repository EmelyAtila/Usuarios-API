package br.com.emelyatila.backend.validates;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
// perguntar sobre tudo nessa classe
@Documented
@Constraint(validatedBy = SenhaConstraintImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaConstraint {

    String message() default """
            A senha deve conter entre 5 e 20 caracteres,
            a senha deve incluir pelo menos uma letra maiúscula (A-Z),
            a senha deve incluir pelo menos uma letra minúscula (a-z),
            a senha deve conter pelo menos um número (0-9),
            a senha deve incluir pelo menos um caractere especial dentre os seguintes: @, $, !, %, *, ?, &.
            """;

    //perguntar porque é obrigatório
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}