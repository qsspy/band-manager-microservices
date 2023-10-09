package com.qsspy.gateway;

import feign.Retryer;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FeignConfiguration {

    @Bean
    OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    Retryer retryer() {
        return new Retryer.Default();
    }
}
