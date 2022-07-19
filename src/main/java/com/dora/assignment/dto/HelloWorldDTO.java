package com.dora.assignment.dto;

import com.dora.assignment.validator.LanguageCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class HelloWorldDTO {
  @LanguageCode private String languageCode;

  @NotBlank(message = "Translation required")
  private String text;
}
