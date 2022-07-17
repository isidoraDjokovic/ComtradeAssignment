package com.dora.assignment.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Constraint(validatedBy = LanguageCodeValidator.class)
@Target( { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LanguageCode {
    int size() default 2;
    String message() default "Invalid language code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
