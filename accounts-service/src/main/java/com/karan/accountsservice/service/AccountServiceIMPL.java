package com.karan.accountsservice.service;

import com.karan.accountsservice.constants.AccountsConstants;
import com.karan.accountsservice.dto.AccountsDTO;
import com.karan.accountsservice.dto.CustomerDTO;
import com.karan.accountsservice.entity.Account;
import com.karan.accountsservice.entity.Customer;
import com.karan.accountsservice.exception.CustomerAlreadyExistsException;
import com.karan.accountsservice.exception.ResourceNotFoundException;
import com.karan.accountsservice.mapper.AccountMapper;
import com.karan.accountsservice.mapper.CustomerMapper;
import com.karan.accountsservice.repository.AccountRepository;
import com.karan.accountsservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceIMPL implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;


    @Override
    public CustomerDTO createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number " + customerDTO.getMobileNumber());
        }


        Customer savedCustomer = customerRepository.save(customer);
        var acc = createNewAccount(savedCustomer);
        Account account = accountRepository.save(acc);

        CustomerDTO savedCusDTO = CustomerMapper.mapToCustomerDto(savedCustomer , new CustomerDTO());
        AccountsDTO accountsDTO = AccountMapper.mapToAccountsDto(account , new AccountsDTO());

        savedCusDTO.setAccountsDTO(accountsDTO);

        return savedCusDTO;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return - Account Details for given Mobile Number
     */
    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer" , "MobileNumber" , mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account" , "CustomerId" , customer.getCustomerId().toString())
        );

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer , new CustomerDTO());
        AccountsDTO accountsDTO = AccountMapper.mapToAccountsDto(account , new AccountsDTO());

        customerDTO.setAccountsDTO(accountsDTO);

        return customerDTO;
    }

    /**
     * @param customerDTO - CustomerDto Object
     * @return boolean indication if the update of Account is successful or not
     */
    @Override
    public Boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();

        if(accountsDTO!=null){

            Account account = accountRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account" , "AccountNumber" , accountsDTO.getAccountNumber().toString())
            );

            AccountMapper.mapToAccounts(accountsDTO , account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer" , "CustomerId" , customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO , customer);
            customerRepository.save(customer);

            isUpdated = true;

        }
        return isUpdated;
    }

    /**
     * @param mobileNumber - Customer Mobile Number
     * @return boolean indication if the deletion of Account is successful or not
     */
    @Override
    public Boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Account createNewAccount(Customer savedCustomer) {
        Account account = new Account();
        account.setCustomerId(savedCustomer.getCustomerId());

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        account.setAccountNumber(randomAccNumber);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);

        return account;
    }




}
