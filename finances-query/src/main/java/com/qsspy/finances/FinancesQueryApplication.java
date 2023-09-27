package com.qsspy.finances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.qsspy.finances"
		}
)
public class FinancesQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancesQueryApplication.class, args);
	}

}
