package com.senla.web.config;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public OkHttpClient getClient() {
        return new OkHttpClient();
    }
}
