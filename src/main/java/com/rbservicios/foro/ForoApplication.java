package com.rbservicios.foro;

import com.rbservicios.foro.domain.model.Role;
import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.infrastructure.persistence.JpaUserRepository;
import com.rbservicios.foro.infrastructure.persistence.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.rbservicios.foro")
public class ForoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepositoryImpl repository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (repository.count() == 0) {
				var administrador = new User(null, "administrador", "admin@correo.com", passwordEncoder.encode("password"),  Role.ADMIN);
				repository.save(administrador);
				System.out.println("Usuario administrador creado");
			}
		};
	}

}
