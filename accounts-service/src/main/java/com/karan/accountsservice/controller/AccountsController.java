package com.karan.accountsservice.controller;

import com.karan.accountsservice.constants.AccountsConstants;
import com.karan.accountsservice.dto.AccountsContactInfoDTO;
import com.karan.accountsservice.dto.CustomerDTO;
import com.karan.accountsservice.dto.DataResponseDTO;
import com.karan.accountsservice.dto.ResponseDTO;
import com.karan.accountsservice.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api" , produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {


    private final IAccountService accountService;

    private final AccountsContactInfoDTO accountsContactInfoDTO;

    private final Environment environment;


    public AccountsController(
            IAccountService accountService ,
            AccountsContactInfoDTO accountsContactInfoDTO,
            Environment environment
    ){
        this.accountService = accountService;
        this.accountsContactInfoDTO = accountsContactInfoDTO;
        this.environment = environment;
    }

    @Value("${build.version}")
    String buildVersion;


    @PostMapping("/create")
    public ResponseEntity<DataResponseDTO<CustomerDTO>> createAccount(
            @Valid
            @RequestBody
            CustomerDTO customerDTO
    ){
        CustomerDTO customer = accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new DataResponseDTO<>(
                        AccountsConstants.STATUS_201,
                        AccountsConstants.MESSAGE_201,
                        customer
                ));
    }


    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(
            @RequestParam
            @Pattern(
                    regexp = "(^$|[0-9]{10})",
                    message = "Mobile number must be 10 digits"
            )
            String mobileNumber
    ){
        CustomerDTO customerDTO = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.ok(customerDTO);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(
            @Valid
            @RequestBody
            CustomerDTO customerDTO
    ){
        Boolean isUpdated = accountService.updateAccount(customerDTO);
        System.out.println(isUpdated);
        if(isUpdated){
            return ResponseEntity.ok(new ResponseDTO(AccountsConstants.STATUS_200 , AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417 , AccountsConstants.MESSAGE_417_UPDATE));
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(
            @RequestParam
            @Pattern(
                    regexp = "(^$|[0-9]{10})",
                    message = "Mobile number must be 10 digits"
            )
            String mobileNumber
    ){
        Boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.ok(new ResponseDTO(AccountsConstants.STATUS_200 , AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417 , AccountsConstants.MESSAGE_417_DELETE));

        }
    }


    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.ok(buildVersion);
    }


    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDTO> getContactInfo(){
        return ResponseEntity.ok(accountsContactInfoDTO);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.ok(
                environment.getProperty("JAVA_HOME")
        );
    }


}
