package com.karan.accountsservice.service;

import com.karan.accountsservice.dto.CustomerDetailsDTO;

public interface ICustomerService {

    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
