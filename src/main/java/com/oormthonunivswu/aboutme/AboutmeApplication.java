package com.oormthonunivswu.aboutme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AboutmeApplication {

	public static void main(String[] args) {
		// 방법 1: System.setProperty를 사용하여 Active Profile 설정
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");

		// 방법 2: SpringApplication 객체를 사용하여 Active Profile 설정
		SpringApplication app = new SpringApplication(AboutmeApplication.class);
		app.setAdditionalProfiles("dev");
		app.run(args);
	}
}
