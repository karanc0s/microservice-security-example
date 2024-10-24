package com.karan.loansservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;

    private String mobileNumber;

    private String loanType;

    private String loanNumber;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;
}
