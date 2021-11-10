package com.CaseStudy.ECart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaDeploy {

	public static void main(String[] args) {
		SpringApplication.run(TaDeploy.class, args);
	}

}
