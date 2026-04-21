package com.bank.cardservice.service.impl;

import com.bank.cardservice.dto.CardRequestDto;
import com.bank.cardservice.dto.CardResponseDto;
import com.bank.cardservice.entity.Card;
import com.bank.cardservice.enums.CardStatus;
import com.bank.cardservice.exception.CustomerCardLimitExceededException;
import com.bank.cardservice.mapper.CardMapper;
import com.bank.cardservice.repository.CardRepository;
import com.bank.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor


public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public CardResponseDto createCard(CardRequestDto request){
        if(cardRepository.countByCustomerId(request.getCustomerId())>=3){
            throw new CustomerCardLimitExceededException("Customer can have maximum 3 cards");
        }
        Card card = new Card();
        card.setCustomerId(request.getCustomerId());
        card.setCardType(request.getCardType());
        card.setCreditLimit(request.getCreditLimit());
        card.setCreatedAt(LocalDateTime.now());
        card.setCardNumber(generateCardNumber());
        card.setCvv(generateCardCvv());
        card.setBalance(BigDecimal.ZERO);
        card.setExpireDate(LocalDate.now().plusYears(3));
        card.setStatus(CardStatus.ACTIVE);
        card.setUpdatedAt(LocalDateTime.now());


         Card savedCard =cardRepository.save(card);

        return cardMapper.toDto(savedCard);

}
  private String generateCardNumber(){

    StringBuilder cardNumber=new  StringBuilder();
    for(int i=0;i<16;i++){
      int digit=(int) (Math.random()*10);
      cardNumber.append(digit);
      }
    return cardNumber.toString();
    }


    private String generateCardCvv(){

        StringBuilder cardCvv= new StringBuilder();
        for(int i =0;i<3;i++){
            int digit =(int) (Math.random()*10);
            cardCvv.append(digit);
        }

        return cardCvv.toString();

    }

}