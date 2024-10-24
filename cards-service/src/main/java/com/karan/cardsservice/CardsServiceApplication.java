package com.karan.cardsservice;

import com.karan.cardsservice.dto.CardsContactInfoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing(auditorAwareRef = "AuditingIMPL")
@EnableConfigurationProperties(CardsContactInfoDTO.class)
public class CardsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsServiceApplication.class, args);
	}

}
