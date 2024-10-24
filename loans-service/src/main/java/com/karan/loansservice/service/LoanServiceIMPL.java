package com.karan.loansservice.service;

import com.karan.loansservice.constants.LoanConstants;
import com.karan.loansservice.dto.LoanDTO;
import com.karan.loansservice.entity.Loan;
import com.karan.loansservice.exception.LoanAlreadyExistsException;
import com.karan.loansservice.exception.ResourceNotFoundException;
import com.karan.loansservice.mapper.LoanMapper;
import com.karan.loansservice.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceIMPL implements ILoanService {

    private LoanRepository loanRepository;

    /**
     * @param mobileNumber - Mobile Number of Customer
     * @return - Response with Loan data
     */
    @Override
    public LoanDTO createLoan(String mobileNumber) {
        Optional<Loan> loan = loanRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent()){
            throw new LoanAlreadyExistsException(mobileNumber);
        }
        Loan newLoan = loanRepository.save(createNewLoan(mobileNumber));

        return LoanMapper.toLoanDTO(newLoan , new LoanDTO());
    }

    /**
     * @param mobileNumber Mobile Number of Customer
     * @return Loan Data based on given Mobile Number
     */
    @Override
    public LoanDTO fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan" , "mobileNumber", mobileNumber)
        );
        return LoanMapper.toLoanDTO(loan, new LoanDTO());
    }

    /**
     * @param loanDTO Loan DTO object
     * @return Boolean indication whether the update of loan details is successful or not
     */
    @Override
    public Boolean updateLoan(LoanDTO loanDTO) {
        Loan loan = loanRepository.findByLoanNumber(loanDTO.getLoanNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Loan" , "LoanNumber", loanDTO.getLoanNumber())
        );
        LoanMapper.toLoan(loanDTO , loan);
        loanRepository.save(loan);

        return true;
    }

    /**
     * @param mobileNumber Mobile Number of Customer
     * @return Boolean indication whether the deletion of loan details is successful or not
     */
    @Override
    public Boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan" , "mobileNumber", mobileNumber)
        );
        loanRepository.delete(loan);
        return true;
    }

    private Loan createNewLoan(String mobileNumber){
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
