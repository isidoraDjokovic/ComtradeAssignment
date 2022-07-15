package com.dora.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class HelloWorldController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("message","Hello World");
        return "index";
    }
}
