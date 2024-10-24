package com.karan.accountsservice.controller;

import com.karan.accountsservice.dto.CustomerDetailsDTO;
import com.karan.accountsservice.service.ICustomerService;
import jakarta.validation.constraints.Pattern;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private final ICustomerService iCustomerService;

    @Autowired
    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber
    ){
        CustomerDetailsDTO customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto);

    }
}
