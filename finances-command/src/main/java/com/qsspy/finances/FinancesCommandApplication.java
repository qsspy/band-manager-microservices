package com.qsspy.finances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.qsspy.finances",
				"com.qsspy.commons.port.output.publisher"
		}
)
public class FinancesCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancesCommandApplication.class, args);
	}

}
