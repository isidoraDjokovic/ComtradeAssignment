package com.dora.assignment.service;

import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class HelloWorldExternalService {

  public String externalHelloWorldTranslate(String languageCode) throws IOException {
    OkHttpClient client = new OkHttpClient();
    String response;
    FormBody body =
        new FormBody.Builder()
            .add("q", "Hello World")
            .add("target", languageCode)
            .add("source", "en")
            .build();
    Request request =
        new Request.Builder()
            .url("https://google-translate1.p.rapidapi.com/language/translate/v2")
            .post(body)
            .addHeader("content-type", "application/x-www-form-urlencoded")
            .addHeader("Accept-Encoding", "application/gzip")
            .addHeader("X-RapidAPI-Key", "740877a791mshdf9915b79b8b2d8p198983jsn0bb4e4518328")
            .addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
            .build();
    response = Objects.requireNonNull(client.newCall(request).execute().body()).string();
    return translation(response);

  }

  public String translation(String result){
    return StringUtils.substringBetween(result, "\"translatedText\":", "}").replace("\"","");
  }
}
