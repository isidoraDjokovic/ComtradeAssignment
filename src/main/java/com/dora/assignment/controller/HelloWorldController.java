package com.dora.assignment.controller;

import com.dora.assignment.service.HelloWorldService;
import com.dora.assignment.validator.LanguageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@Validated
@RequiredArgsConstructor
public class HelloWorldController {

    @Autowired
    private final HelloWorldService service;

    @GetMapping("hello")
    public String hello(@RequestParam("language_code") @LanguageCode String languageCode, Model model) {
        model.addAttribute("message",service.findByLanguageCode(languageCode).getText());
        model.addAttribute("language",service.findByLanguageCode(languageCode).getLanguageCode());
        return "index";
    }
}
