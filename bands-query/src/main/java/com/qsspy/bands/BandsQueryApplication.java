package com.qsspy.bands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.qsspy.bands"
		}
)
public class BandsQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BandsQueryApplication.class, args);
	}

}
