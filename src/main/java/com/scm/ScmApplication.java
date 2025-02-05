package com.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ScmApplication {

	 Dotenv dotenv = Dotenv.load();

	public static void main(String[] args) {
		SpringApplication.run(ScmApplication.class, args);
	}
}
