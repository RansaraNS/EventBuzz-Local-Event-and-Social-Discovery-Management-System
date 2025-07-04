package com.example.eventbuzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main application class for EventBuzz Backend
 * Enables JPA auditing for automatic timestamp management
 * Enables async processing for email/SMS services
 * Enables transaction management for database operations
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableTransactionManagement
@EnableConfigurationProperties
public class EventbuzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventbuzzApplication.class, args);
	}

}
