package com.karan.accountsservice.service;

import com.karan.accountsservice.dto.*;
import com.karan.accountsservice.entity.Account;
import com.karan.accountsservice.entity.Customer;
import com.karan.accountsservice.exception.ResourceNotFoundException;
import com.karan.accountsservice.mapper.AccountMapper;
import com.karan.accountsservice.mapper.CustomerMapper;
import com.karan.accountsservice.repository.AccountRepository;
import com.karan.accountsservice.repository.CustomerRepository;
import com.karan.accountsservice.service.feign.FeignCardClient;
import com.karan.accountsservice.service.feign.FeignLoanClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceIMPL implements ICustomerService{


    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final FeignCardClient cardClient;
    private final FeignLoanClient loanClient;

    @Autowired
    public CustomerServiceIMPL(
            AccountRepository accountRepository,
            CustomerRepository customerRepository,
            FeignCardClient cardClient,
            FeignLoanClient loanClient
    ) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.cardClient = cardClient;
        this.loanClient = loanClient;
    }


    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDTO customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDTO());
        customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDTO()));

        ResponseEntity<DataResponseDTO<LoanDTO>> loansDtoResponseEntity = loanClient.fetchLoanDetails(mobileNumber);
        if(loansDtoResponseEntity.getBody()!=null){
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody().getData());
        }

        ResponseEntity<DataResponseDTO<CardDTO>> cardsDtoResponseEntity = cardClient.fetchCardDetails(mobileNumber);
        if(cardsDtoResponseEntity.getBody()!=null){
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody().getData());
        }

        return customerDetailsDto;
    }

}
