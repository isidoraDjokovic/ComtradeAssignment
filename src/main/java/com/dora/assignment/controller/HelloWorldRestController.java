package com.dora.assignment.controller;

import com.dora.assignment.aop.Log;
import com.dora.assignment.service.HelloWorldService;
import com.dora.assignment.validator.LanguageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HelloWorldRestController {

  private final HelloWorldService service;

  @Log
  @GetMapping("hello-rest")
  @ResponseStatus(HttpStatus.OK)
  public String helloRest(@RequestParam("language_code") @LanguageCode String languageCode) {
    return service.findByLanguageCode(languageCode).getText();
  }
}
