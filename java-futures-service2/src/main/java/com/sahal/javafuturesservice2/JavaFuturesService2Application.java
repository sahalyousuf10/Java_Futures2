package com.sahal.javafuturesservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JavaFuturesService2Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaFuturesService2Application.class, args);
	}

}
