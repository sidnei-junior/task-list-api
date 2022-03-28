package com.tasklist.tasklistapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TaskListApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskListApiApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder getPasswordEnconder( ) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
//	private final PasswordEncoder passwordEncoder;
//	private String senhaTeste = "troque123";
//	
//	private String encryptSenha = senhaTeste.passwordEncoder(passwordEncoder);

}
