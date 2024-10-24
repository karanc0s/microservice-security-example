package com.karan.accountsservice.dto;


import com.karan.accountsservice.constants.MessagesConstants;
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
public class CardDTO {

    @Pattern(
            regexp = "(^$|[0-9]{10})",
            message = MessagesConstants.ERR_MSG_INVALID_MOBILE_NUMBER
    )
    @NotEmpty(message = MessagesConstants.ERR_MSG_MOBILE_NUMBER_EMPTY)
    private String mobileNumber;


    @Pattern(
            regexp = "(^$|[0-9]{12})",
            message = MessagesConstants.ERR_MSG_INVALID_CARD_NUMBER
    )
    @NotEmpty(message = MessagesConstants.ERR_MSG_INVALID_MOBILE_NUMBER)
    private String cardNumber;

    @NotEmpty(message = MessagesConstants.ERR_MSG_EMPTY_CARD_TYPE)
    private String cardType;

    @Positive(message = MessagesConstants.ERR_MSG_POSITIVE_CARD_LIMIT)
    private Integer totalLimit;

    @PositiveOrZero(message = MessagesConstants.ERR_MSG_POSITIVE_LIMIT_USED)
    private Integer amountUsed;

    @PositiveOrZero(message = MessagesConstants.ERR_MSG_POSITIVE_PENDING_LIMIT)
    private Integer availableAmount;

}
