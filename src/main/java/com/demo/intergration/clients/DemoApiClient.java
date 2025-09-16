package com.demo.intergration.clients;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.demo.intergration.dto.DemoResponse;
import com.demo.shared.properties.DemoProperties;
import com.demo.shared.properties.DemoProperties;

@Component
public class DemoApiClient {

    private final RestClient restClient;
    private static final Logger logger = LogManager.getLogger(DemoApiClient.class);

  
       private final DemoProperties demoProperties;


    public DemoApiClient(@Qualifier("demoClient") RestClient restClient,DemoProperties demoProperties) {
        this.restClient = restClient;
        this.demoProperties = demoProperties;
    }


   public DemoResponse doSomething(String query) {
    try {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/route")
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .body(DemoResponse.class);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}



}
