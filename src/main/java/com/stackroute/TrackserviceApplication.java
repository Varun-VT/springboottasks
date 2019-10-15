package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication    /*same as @Configuration @EnableAutoConfiguration @ComponentScan*/
public class TrackserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(TrackserviceApplication.class, args);
	}

}
