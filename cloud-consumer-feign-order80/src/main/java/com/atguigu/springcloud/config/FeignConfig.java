package com.atguigu.springcloud.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level getLoggerLevel() {
        return Logger.Level.FULL;
    }
}
