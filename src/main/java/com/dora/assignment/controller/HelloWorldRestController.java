package com.dora.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class HelloWorldRestController {

    @GetMapping("hello-rest")
    @ResponseStatus(HttpStatus.OK)
    public String helloRest(){
        return "Hello World";
    }
}
