package com.rungroop.web;

import com.rungroop.web.models.Role;
import com.rungroop.web.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(RoleRepository roleRepository) {
		return args -> {
			// Initialize USER role if it doesn't exist
			if (roleRepository.findByName("USER") == null) {
				Role userRole = new Role();
				userRole.setName("USER");
				roleRepository.save(userRole);
				System.out.println("USER role created successfully");
			}

			// Initialize ADMIN role if it doesn't exist
			if (roleRepository.findByName("ADMIN") == null) {
				Role adminRole = new Role();
				adminRole.setName("ADMIN");
				roleRepository.save(adminRole);
				System.out.println("ADMIN role created successfully");
			}
		};
	}

}
