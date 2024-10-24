package com.karan.loansservice.service;

import com.karan.loansservice.dto.LoanDTO;

public interface ILoanService {

    /**
     * @param mobileNumber - Mobile Number of Customer
     * @return - Response with Loan data
     */
    LoanDTO createLoan(String mobileNumber);


    /**
     * @param mobileNumber  Mobile Number of Customer
     * @return Loan Data based on given Mobile Number
     */
    LoanDTO fetchLoan(String mobileNumber);

    /**
     * @param loanDTO  Loan DTO object
     * @return Boolean indication whether the update of loan details is successful or not
     */
    Boolean updateLoan(LoanDTO loanDTO);


    /**
     * @param mobileNumber Mobile Number of Customer
     * @return Boolean indication whether the deletion of loan details is successful or not
     */
    Boolean deleteLoan(String mobileNumber);
}
