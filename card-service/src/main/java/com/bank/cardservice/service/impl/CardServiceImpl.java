package com.bank.cardservice.service.impl;

import com.bank.cardservice.dto.CardRequestDto;
import com.bank.cardservice.dto.CardResponseDto;
import com.bank.cardservice.dto.UpdateCardRequestDto;
import com.bank.cardservice.entity.Card;
import com.bank.cardservice.enums.CardStatus;
import com.bank.cardservice.enums.CardType;
import com.bank.cardservice.exception.CardNotFoundException;
import com.bank.cardservice.exception.CustomerCardLimitExceededException;
import com.bank.cardservice.exception.InvalidCardOperationException;
import com.bank.cardservice.exception.NoFieldsProvidedForUpdateException;
import com.bank.cardservice.mapper.CardMapper;
import com.bank.cardservice.repository.CardRepository;
import com.bank.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<CardResponseDto> getAllCards(){
       List<Card> cards=cardRepository.findAll();

       return cards.stream().map(cardMapper::toDto).toList();

    }

    @Override
    public CardResponseDto getCardById(Long id) {
      Card card= cardRepository.findById(id).
              orElseThrow(()-> new CardNotFoundException("Card nopt found this id:"+id));

      return cardMapper.toDto(card);
    }

    @Override
    public CardResponseDto updateCard(Long id, UpdateCardRequestDto request) {

        Card card = cardRepository.findById(id).
                orElseThrow(() -> new CardNotFoundException("Card not found with id: "+id));

         if(request.getCreditLimit()==null &&request.getExpireDate()==null){
             throw new NoFieldsProvidedForUpdateException("No fields provided for update");
         }
            if (request.getCreditLimit() != null) {
                if(card.getCardType().equals(CardType.CREDIT)) {
                card.setCreditLimit(request.getCreditLimit());

                }else throw new InvalidCardOperationException("Credit limit can only be updated for credit cards");
            }



        if(request.getExpireDate()!=null){
            card.setExpireDate(request.getExpireDate());
        }
       card.setUpdatedAt(LocalDateTime.now());

        Card savedCard=cardRepository.save(card);

        return cardMapper.toDto(savedCard);
    }


}