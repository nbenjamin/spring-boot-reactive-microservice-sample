package com.nbenja.store.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OrderserviceApplication {

	public static void main(String[] args) {
		//SpringApplication.run(OrderserviceApplication.class, args);

		//New Fluent Application Builder API, where we can build applicationContext hierachy
		new SpringApplicationBuilder(OrderserviceApplication.class).web(WebApplicationType.REACTIVE).run(args);

	}

}

