package com.oormthonunivswu.aboutme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AboutmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AboutmeApplication.class, args);
	}

}
