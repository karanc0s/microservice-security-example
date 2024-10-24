package com.karan.loansservice;

import com.karan.loansservice.dto.LoanContactInfoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing(auditorAwareRef = "AuditingIMPL")
@EnableConfigurationProperties(LoanContactInfoDTO.class)
public class LoansServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansServiceApplication.class, args);
    }

}
