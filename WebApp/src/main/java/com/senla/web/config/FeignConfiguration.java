package com.senla.web.config;

import com.fasterxml.jackson.databind.Module;
import feign.okhttp.OkHttpClient;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public OkHttpClient getClient() {
        return new OkHttpClient();
    }

    @Bean
    public Module pageJacksonModule() {
        return new PageJacksonModule();
    }

    @Bean
    public Module sortJacksonModule() {
        return new SortJacksonModule();
    }
}
