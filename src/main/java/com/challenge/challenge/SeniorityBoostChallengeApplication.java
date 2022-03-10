package com.challenge.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SeniorityBoostChallengeApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(SeniorityBoostChallengeApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("*").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

	@Override
	public void run(String... args) throws Exception {
		template.execute("DROP TABLE TEXT IF EXISTS");
		template.execute("CREATE TABLE TEXT(id INTEGER(11) PRIMARY KEY auto_increment,hash VARCHAR(32),chars INTEGER(11),result VARCHAR,active BOOLEAN)");
	}
}
