package com.karan.loansservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans-contact-info")
@Getter @Setter
public class LoanContactInfoDTO {

    private String message;
    private Map<String , String> contactDetails;
    private List<String> onCallSupport;
}
