package com.dora.assignment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "dora.assignment.web-client")
public class WebClientConfigurationProperties {

    private String url;
    private List<Header> headers;


    @Data
    public static class Header {
        private String name;
        private String value;
    }
}
