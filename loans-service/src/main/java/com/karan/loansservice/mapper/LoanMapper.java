package com.karan.loansservice.mapper;

import com.karan.loansservice.dto.LoanDTO;
import com.karan.loansservice.entity.Loan;

public class LoanMapper {

    public static LoanDTO toLoanDTO(Loan loan , LoanDTO loanDTO) {
        loanDTO.setLoanNumber(loan.getLoanNumber());
        loanDTO.setLoanType(loan.getLoanType());
        loanDTO.setMobileNumber(loan.getMobileNumber());
        loanDTO.setTotalLoan(loan.getTotalLoan());
        loanDTO.setAmountPaid(loan.getAmountPaid());
        loanDTO.setOutstandingAmount(loan.getOutstandingAmount());

        return loanDTO;
    }


    public static Loan toLoan(LoanDTO loansDto, Loan loans) {
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }
}
