package com.example.demo;

import com.example.demo.dbauth.ApplicationUserDao;
import com.example.demo.dbauth.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(ApplicationUserDao applicationUserDao){
//		return args -> {
//			applicationUserDao.save(new UserRole("trainee", passwordEncoder.encode("trainee"), "ROLE_TRAINEE"));
//		};
//	}

}
