package com.deepblue.SDKServer.configure;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class CommonConfigure {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

