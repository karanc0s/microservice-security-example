package com.karan.accountsservice.service.feign;

import com.karan.accountsservice.dto.DataResponseDTO;
import com.karan.accountsservice.dto.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans-service")
public interface FeignLoanClient {

    @GetMapping("/api/fetch")
    ResponseEntity<DataResponseDTO<LoanDTO>> fetchLoanDetails(@RequestParam String mobileNumber);

}
