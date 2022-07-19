package com.dora.assignment.service;

import com.dora.assignment.entity.HelloWorld;
import com.dora.assignment.exception.NotFoundException;
import com.dora.assignment.repository.SpringDataHelloWorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloWorldService {
  @Autowired private final SpringDataHelloWorldRepository repository;

  public Iterable<HelloWorld> findAll() {
    return repository.findAll();
  }

  public void save(HelloWorld helloWorld) {
    repository.save(helloWorld);
  }

  public void delete(String languageCode) {
    HelloWorld saved = findByLanguageCode(languageCode);
    repository.delete(saved);
  }

  public HelloWorld findByLanguageCode(String languageCode) {
    return repository
        .findByLanguageCode(languageCode)
        .orElseThrow(() -> new NotFoundException("No translation found"));
  }
}
