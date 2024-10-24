package com.karan.loansservice.dto;


import com.karan.loansservice.constants.MessagesConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    @Pattern(
            regexp = "(^$|[0-9]{10})",
            message = MessagesConstants.ERR_MSG_INVALID_MOBILE_NUMBER
    )
    @NotEmpty(message = MessagesConstants.ERR_MSG_MOBILE_NUMBER_EMPTY)
    private String mobileNumber;

    @NotEmpty(message = MessagesConstants.ERR_MSG_LOAN_TYPE_EMPTY)
    private String loanType;

    @Pattern(
            regexp = "(^$|[0-9]{12})",
            message = MessagesConstants.ERR_MSG_INVALID_LOAN_NUMBER
    )
    @NotEmpty(message = MessagesConstants.ERR_MSG_LOAN_NUMBER_EMPTY)
    private String loanNumber;

    @Positive(message = MessagesConstants.ERR_MSG_POSITIVE_LOAN_AMT)
    private int totalLoan;

    @PositiveOrZero(message = MessagesConstants.ERR_MSG_POSITIVE_ZR_LOAN_PAID_AMT)
    private int amountPaid;

    @PositiveOrZero(message = MessagesConstants.ERR_MSG_POSITIVE_ZR_OUTSTANDING_AMT)
    private int outstandingAmount;
}
