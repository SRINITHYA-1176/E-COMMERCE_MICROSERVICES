package com.srinithya.microservices.order.config;

import com.srinithya.microservices.order.client.InventoryClient;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    // if u have multiple properties to bind then u can use @ConfigurationProperties
    @Value("${inventory.url}")
    private String inventoryServiceUrl;

    private final ObservationRegistry observationRegistry;

    @Bean
    public InventoryClient inventoryClient(){

        RestClient.Builder builder = RestClient.builder();
        builder.baseUrl(inventoryServiceUrl);
        builder.requestFactory(getClientRequestFactory());
        builder.observationRegistry(observationRegistry);
        RestClient  restClient = builder
                .build();
      var restClientAdapter = RestClientAdapter.create(restClient);
      var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
      return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

    private ClientHttpRequestFactory getClientRequestFactory() {
        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings  = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(3))
                .withReadTimeout(Duration.ofSeconds(3));
        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
    }


}

