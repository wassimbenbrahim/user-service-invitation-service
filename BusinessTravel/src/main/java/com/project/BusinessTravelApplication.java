package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2

@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
public class BusinessTravelApplication {
	public static void main(String[] args) {
		SpringApplication.run(BusinessTravelApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ENTREPRISE"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "wassim","benbrahim","wassim benbrahim", "wassim","benbrahimwassim67@hotmail.fr ", "1234", new ArrayList<>()));
			userService.addRoleToUser("wassim", "ROLE_SUPER_ADMIN");
			
		};
	}*/


}
