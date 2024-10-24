package com.karan.accountsservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

    @Column(name = "cusomer_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;
}
