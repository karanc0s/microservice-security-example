package com.karan.accountsservice.service;

import com.karan.accountsservice.dto.CustomerDTO;


public interface IAccountService {

    /**
     *
     * @param account - CustomerDTO Object
     */
    CustomerDTO createAccount(CustomerDTO account);


    /**
     * @param mobileNumber - Input Mobile Number
     * @return - Account Details for given Mobile Number
     */
    CustomerDTO fetchAccount(String mobileNumber);


    /**
     * @param account - CustomerDto Object
     * @return boolean indication if the update of Account is successful or not
     */
    Boolean updateAccount(CustomerDTO account);


    /**
     * @param mobileNumber - Customer Mobile Number
     * @return boolean indication if the deletion of Account is successful or not
     */
    Boolean deleteAccount(String mobileNumber);
}
