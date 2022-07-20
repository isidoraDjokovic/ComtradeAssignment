package com.dora.assignment.controller;

import com.dora.assignment.service.HelloWorldExternalService;
import com.dora.assignment.validator.LanguageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/external")
@RequiredArgsConstructor
@Validated
public class HelloWorldExternal {

  private final HelloWorldExternalService service;

  @GetMapping("hello")
  @ResponseStatus(HttpStatus.OK)
  public String callExternalApi(@RequestParam("language_code") @LanguageCode String languageCode) throws IOException {
    return service.externalHelloWorldTranslate(languageCode);
  }
}
