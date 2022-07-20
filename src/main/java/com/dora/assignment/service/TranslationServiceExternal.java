package com.dora.assignment.service;

import com.dora.assignment.aop.Log;
import com.dora.assignment.config.WebClientConfigurationProperties;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Profile("external")
@RequiredArgsConstructor
public class TranslationServiceExternal implements ITranslationService {

  private final WebClientConfigurationProperties webClientConfigurationProperties;

  @Log
  @SneakyThrows
  public String translate(String languageCode) {
    String word = "Hello World";
    String defaultLanguageCode = "en";
    if(languageCode.equals(defaultLanguageCode))
      return word;
    OkHttpClient client = new OkHttpClient();
    String response;
    FormBody body =
        new FormBody.Builder()
            .add("q", word)
            .add("target", languageCode)
            .add("source", defaultLanguageCode)
            .build();
    var request =
        new Request.Builder()
            .url(webClientConfigurationProperties.getUrl())
            .post(body);
    webClientConfigurationProperties.getHeaders()
        .forEach(header -> request.addHeader(header.getName(), header.getValue()));
    response = Objects.requireNonNull(client.newCall(request.build()).execute().body()).string();
    return translation(response);

  }

  public String translation(String result){
    return StringUtils.substringBetween(result, "\"translatedText\":", "}").replace("\"","");
  }
}
