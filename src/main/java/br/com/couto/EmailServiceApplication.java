package br.com.couto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/ms-email/api");
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
