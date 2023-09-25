package com.qsspy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@SpringBootApplication(
		scanBasePackages = {
				"com.qsspy.authservice",
				"com.qsspy.gateway"
		}
)
@EnableJpaRepositories(
		basePackages = {
				"com.qsspy.authservice",
		}
)
@EntityScan(
		basePackages = {
				"com.qsspy.authservice",
		}
)
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
