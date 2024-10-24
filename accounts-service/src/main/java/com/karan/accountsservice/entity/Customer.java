package com.karan.accountsservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;
}
