package com.idruide.backend.packingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.idruide.backend.packingservice.entities")
public class PackingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PackingserviceApplication.class, args);
	}

}
