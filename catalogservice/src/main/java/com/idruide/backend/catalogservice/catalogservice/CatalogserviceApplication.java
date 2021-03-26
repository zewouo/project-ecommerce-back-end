package com.idruide.backend.catalogservice.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 *
 *
 * @author Thierry Kwekam
 */
@SpringBootApplication
@EntityScan("com.idruide.backend.catalogservice.catalogservice.entities")
public class CatalogserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogserviceApplication.class, args);
	}

}
