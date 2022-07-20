package com.dora.assignment.service;

import com.dora.assignment.aop.Log;
import com.dora.assignment.entity.HelloWorld;
import com.dora.assignment.exception.NotFoundException;
import com.dora.assignment.repository.SpringDataHelloWorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloWorldService {
  private final SpringDataHelloWorldRepository repository;

  public Iterable<HelloWorld> findAll() {
    return repository.findAll();
  }

  @Log
  public void save(HelloWorld helloWorld) {
    repository.save(helloWorld);
  }


  @Log
  public void delete(String languageCode) {
    HelloWorld saved = findByLanguageCode(languageCode);
    repository.delete(saved);
  }

  @Log
  public HelloWorld findByLanguageCode(String languageCode) {
    return repository
        .findByLanguageCode(languageCode)
        .orElseThrow(() -> new NotFoundException("No translation found"));
  }
}
