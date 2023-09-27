package com.qsspy.calendars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.qsspy.calendars"
		}
)
public class CalendarsQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarsQueryApplication.class, args);
	}

}
