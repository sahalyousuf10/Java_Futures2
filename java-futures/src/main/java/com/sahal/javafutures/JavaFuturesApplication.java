package com.sahal.javafutures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFeignClients
@SpringBootApplication
public class JavaFuturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaFuturesApplication.class, args);
	}

}
