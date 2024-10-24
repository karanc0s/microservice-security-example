package com.karan.cardsservice.service;

import com.karan.cardsservice.constants.CardConstants;
import com.karan.cardsservice.dto.CardDTO;
import com.karan.cardsservice.entity.Card;
import com.karan.cardsservice.exception.CardAlreadyExistsException;
import com.karan.cardsservice.exception.ResourceNotFoundException;
import com.karan.cardsservice.mapper.CardMapper;
import com.karan.cardsservice.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardServiceIMPL implements ICardService{

    @Autowired
    private CardsRepository cardRepository;

    @Override
    public CardDTO createCard(String mobileNumber) {
        Optional<Card> card = cardRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists");
        }
        Card newCard = cardRepository.save(createNewCard(mobileNumber));
        return CardMapper.mapToCardsDto(newCard , new CardDTO());
    }

    @Override
    public CardDTO fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.mapToCardsDto(card , new CardDTO());
    }

    @Override
    public Boolean updateCard(CardDTO cardDTO) {
        Card card = cardRepository.findByMobileNumber(cardDTO.getMobileNumber()).orElseThrow(
                () -> new CardAlreadyExistsException("Card already exists")
        );
        CardMapper.mapToCards(cardDTO, card);
        cardRepository.save(card);
        return true;
    }

    @Override
    public Boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(card.getCardId());
        return true;
    }


    private Card createNewCard(String mobileNumber){
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
