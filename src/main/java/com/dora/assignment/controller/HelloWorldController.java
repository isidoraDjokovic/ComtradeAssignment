package com.dora.assignment.controller;

import com.dora.assignment.dto.HelloWorldDTO;
import com.dora.assignment.entity.HelloWorld;
import com.dora.assignment.service.HelloWorldService;
import com.dora.assignment.service.ITranslationService;
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

  private final ITranslationService service;
  private final HelloWorldService internalService;

  @GetMapping("hello")
  public String hello(
      @RequestParam("language_code") @LanguageCode String languageCode, Model model) {
    model.addAttribute("message", service.translate(languageCode));
    model.addAttribute("language", languageCode);
    return "index";
  }

  @GetMapping("secure/hello")
  public String login() {
    return "login";
  }

  @GetMapping("/user")
  public String user() {
    return "user";
  }

  @GetMapping("admin")
  public String admin(Model model) {
    model.addAttribute("translations", internalService.findAll());
    model.addAttribute("helloWorld", new HelloWorld());
    return "admin";
  }

  @PostMapping("admin")
  public String addPair(@ModelAttribute("helloWorld") @Valid HelloWorldDTO helloWorld) {
    internalService.save(
        new HelloWorld()
            .setText(helloWorld.getText())
            .setLanguageCode(helloWorld.getLanguageCode()));
    return "redirect:/admin";
  }

  @GetMapping("admin/{languageCode}")
  public String delete(@PathVariable("languageCode") String languageCode) {
    internalService.delete(languageCode);
    return "redirect:/admin";
  }
}
