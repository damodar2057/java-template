package com.demo.shared.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "external-services.demo")
public class DemoProperties {
    private String apiKey;
    private String baseUrl;
    
}
