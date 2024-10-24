package com.karan.accountsservice.constants;

public final class MessagesConstants {

    private MessagesConstants(){}

    public static final String ERR_MSG_MOBILE_NUMBER_EMPTY = "Mobile number cannot be empty";
    public static final String ERR_MSG_INVALID_MOBILE_NUMBER = "Mobile number is invalid";

    public static final String ERR_MSG_INVALID_CARD_NUMBER = "card number must be 12 digits";
    public static final String ERR_MSG_EMPTY_CARD_TYPE = "Card type cannot be empty";
    public static final String ERR_MSG_INVALID_CARD_TYPE = "Card type is invalid";
    public static final String ERR_MSG_INVALID_CARD = "Card is invalid";

    public static final String ERR_MSG_POSITIVE_CARD_LIMIT = "Card limit cannot be negative";
    public static final String ERR_MSG_POSITIVE_LIMIT_USED = "Total amount used should be positive";
    public static final String ERR_MSG_POSITIVE_PENDING_LIMIT = "Total amount pending should be positive";

    public static final String ERR_MSG_INVALID_LOAN_NUMBER = "Loan number must be 12 digits";
    public static final String ERR_MSG_LOAN_NUMBER_EMPTY = "Loan number is cannot be empty";
    public static final String ERR_MSG_LOAN_TYPE_EMPTY = "Loan type cannot be empty";
    public static final String ERR_MSG_POSITIVE_LOAN_AMT = "Total loan amount should be greater than zero";
    public static final String ERR_MSG_POSITIVE_ZR_LOAN_PAID_AMT = "Total loan amount paid should be greater than or equal to zero";
    public static final String ERR_MSG_POSITIVE_ZR_OUTSTANDING_AMT = "Total outstanding amount paid should be greater than or equal to zero";



}
