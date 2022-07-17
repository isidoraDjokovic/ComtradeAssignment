package com.dora.assignment.service;

import com.dora.assignment.entity.HelloWorld;
import com.dora.assignment.exception.NotFoundException;
import com.dora.assignment.repository.SpringDataHelloWorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HelloWorldService {
    @Autowired
    private final SpringDataHelloWorldRepository repository;

    public List<HelloWorld> findAll(){
        return (List<HelloWorld>) repository.findAll();
    }

    public void save(HelloWorld helloWorld){
       repository.save(helloWorld);
    }

    public HelloWorld findByLanguageCode(String languageCode)
    {
        return repository.findByLanguageCode(languageCode).orElseThrow(() -> new NotFoundException("No translation found"));
    }


}
