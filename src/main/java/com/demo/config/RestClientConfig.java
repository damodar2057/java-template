package com.demo.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.shared.properties.DemoProperties;

@Configuration
public class RestClientConfig {

    @Value("${external-services.demo.url}")
    private String demoApiUrl;


    private final DemoProperties demoProperties;

    public RestClientConfig(DemoProperties demoProperties){
        this.demoProperties = demoProperties;
    }

    @Bean
    public WebClient demoWebClient() {
        return WebClient.builder()
                .baseUrl(demoApiUrl)
                .defaultHeader("User-Agent", "TollServiceApp/1.0")
                .defaultHeader("Bearer ", demoProperties.getApiKey())
                .build();
    }

    @Bean
    @Qualifier("demoClient")
    public RestClient demoRestClient() {
        return RestClient.builder()
                .baseUrl(demoApiUrl)
                .defaultHeader("User-Agent", "TollServiceApp/1.0")
                // .requestInterceptor(myCustomInterceptor)
                // .requestInitializer(myCustomInitializer)
                .build();
    }


}
