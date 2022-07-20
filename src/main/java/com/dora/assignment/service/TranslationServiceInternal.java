package com.dora.assignment.service;

import com.dora.assignment.aop.Log;
import com.dora.assignment.exception.NotFoundException;
import com.dora.assignment.repository.SpringDataHelloWorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("internal")
public class TranslationServiceInternal implements ITranslationService {

  private final SpringDataHelloWorldRepository repository;

  @Log
  @Override
  public String translate(String languageCode) {
    return repository
        .findByLanguageCode(languageCode)
        .orElseThrow(() -> new NotFoundException("No translation found"))
        .getText();
  }
}
