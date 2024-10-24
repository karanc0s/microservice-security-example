package com.karan.cardsservice.service;

import com.karan.cardsservice.dto.CardDTO;


public interface ICardService {

    CardDTO createCard(String mobileNumber);

    CardDTO fetchCard(String mobileNumber);

    Boolean updateCard(CardDTO cardDTO);

    Boolean deleteCard(String mobileNumber);

}
