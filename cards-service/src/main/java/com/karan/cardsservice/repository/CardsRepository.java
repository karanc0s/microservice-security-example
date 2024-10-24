package com.karan.cardsservice.repository;

import com.karan.cardsservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CardsRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCardNumber(String cardNumber);

    Optional<Card> findByMobileNumber(String mobileNumber);

}
