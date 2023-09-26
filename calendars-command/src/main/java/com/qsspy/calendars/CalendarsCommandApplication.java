package com.qsspy.calendars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.qsspy.calendars",
				"com.qsspy.commons.port.output.publisher"
		}
)
public class CalendarsCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarsCommandApplication.class, args);
	}

}
