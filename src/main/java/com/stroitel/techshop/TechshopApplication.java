package com.stroitel.techshop;

import com.stroitel.techshop.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import({SecurityConfig.class})
@SpringBootApplication
public class TechshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechshopApplication.class, args);
	}

}
