package com.karan.accountsservice;

import com.karan.accountsservice.dto.AccountsContactInfoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingIMPL")
@EnableConfigurationProperties(AccountsContactInfoDTO.class)
@EnableFeignClients
@EnableDiscoveryClient
public class AccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}

}
