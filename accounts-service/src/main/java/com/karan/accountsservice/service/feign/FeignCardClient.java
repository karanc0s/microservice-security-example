package com.karan.accountsservice.service.feign;

import com.karan.accountsservice.dto.CardDTO;
import com.karan.accountsservice.dto.DataResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards-service")
public interface FeignCardClient {

    @GetMapping("/api/fetch")
    ResponseEntity<DataResponseDTO<CardDTO>> fetchCardDetails(@RequestParam String mobileNumber);
}
