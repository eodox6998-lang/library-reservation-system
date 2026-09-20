package com.example.demo;
import java.time.Clock;
import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;


@Configuration 

public class Timestamp {
    
    @Bean
    public Clock clock(){
        return Clock.systemDefaultZone(); 
    }
    @Bean
    public RestTemplate restTemplate(){
       SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
       factory.setConnectTimeout(Duration.ofSeconds(5));
       factory.setReadTimeout(Duration.ofSeconds(10));

       return new RestTemplate(factory);
    }

    
}
