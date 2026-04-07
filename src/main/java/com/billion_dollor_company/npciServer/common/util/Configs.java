package com.billion_dollor_company.npciServer.common.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configs {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
