package com.karan.cardsservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;

    private String mobileNumber;

    private String cardNumber;

    private String cardType;

    private Integer totalLimit;

    private Integer amountUsed;

    private Integer availableAmount;
}
