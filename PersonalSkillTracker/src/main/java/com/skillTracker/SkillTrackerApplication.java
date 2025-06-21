package com.skillTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.controller", "com.repository", "com.service", "com.skillTracker"})
@EnableJpaRepositories(basePackages = "com.repository")
public class SkillTrackerApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SkillTrackerApplication.class, args);
	}

}
