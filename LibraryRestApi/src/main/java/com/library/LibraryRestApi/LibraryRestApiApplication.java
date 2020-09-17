package com.library.LibraryRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.library.LibraryRestApi.dao.EmprunteurDao;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = EmprunteurDao.class)
public class LibraryRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryRestApiApplication.class, args);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
