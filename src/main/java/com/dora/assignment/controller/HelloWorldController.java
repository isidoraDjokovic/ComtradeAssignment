package com.dora.assignment.controller;

import com.dora.assignment.aop.Log;
import com.dora.assignment.dto.HelloWorldDTO;
import com.dora.assignment.entity.HelloWorld;
import com.dora.assignment.service.HelloWorldService;
import com.dora.assignment.validator.LanguageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping
@Validated
@RequiredArgsConstructor
public class HelloWorldController {

  private final HelloWorldService service;

  @Log
  @GetMapping("hello")
  public String hello(
      @RequestParam("language_code") @LanguageCode String languageCode, Model model) {
    model.addAttribute("message", service.findByLanguageCode(languageCode).getText());
    model.addAttribute("language", service.findByLanguageCode(languageCode).getLanguageCode());
    return "index";
  }

  @GetMapping("secure/hello")
  public String login() {
    return "login";
  }

  @GetMapping("user")
  public String user() {
    return "user";
  }

  @GetMapping("admin")
  public String admin(Model model) {
    model.addAttribute("translations", service.findAll());
    model.addAttribute("helloWorld", new HelloWorld());
    return "admin";
  }

  @Log
  @PostMapping("admin")
  public String addPair(@ModelAttribute("helloWorld") @Valid HelloWorldDTO helloWorld) {
    service.save(
        new HelloWorld()
            .setText(helloWorld.getText())
            .setLanguageCode(helloWorld.getLanguageCode()));
    return "redirect:/admin";
  }

  @Log
  @GetMapping("admin/{languageCode}")
  public String delete(@PathVariable("languageCode") String languageCode) {
    service.delete(languageCode);
    return "redirect:/admin";
  }
}
