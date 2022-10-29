package com.gft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetoMvcGftApplication {
	public static void main(String[] args) {
		System.out.print(new BCryptPasswordEncoder().encode("1234"));
		SpringApplication.run(ProjetoMvcGftApplication.class, args);
	}
}