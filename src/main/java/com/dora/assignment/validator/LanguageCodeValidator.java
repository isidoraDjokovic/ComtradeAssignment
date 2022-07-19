package com.dora.assignment.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;
import java.util.Set;

public class LanguageCodeValidator implements ConstraintValidator<LanguageCode, String> {

  private int size;
  private static final Set<String> ISO_LANGUAGES = Set.of(Locale.getISOLanguages());

  @Override
  public void initialize(LanguageCode languageCode) {
    this.size = languageCode.size();
  }

  @Override
  public boolean isValid(String languageCode, ConstraintValidatorContext context) {

    return languageCode != null
        && languageCode.length() == this.size
        && ISO_LANGUAGES.contains(languageCode);
  }
}
