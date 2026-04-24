package com.bank.cardservice.service.impl;

import com.bank.cardservice.dto.*;
import com.bank.cardservice.entity.Card;
import com.bank.cardservice.enums.CardStatus;
import com.bank.cardservice.enums.CardType;
import com.bank.cardservice.exception.*;
import com.bank.cardservice.mapper.CardMapper;
import com.bank.cardservice.repository.CardRepository;
import com.bank.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
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

        isCardExpired(card);


      return cardMapper.toDto(card);
    }

    @Override
    public CardResponseDto updateCard(Long id, UpdateCardRequestDto request) {

        Card card = cardRepository.findById(id).
                orElseThrow(() -> new CardNotFoundException("Card not found with id: "+id));

        isCardExpired(card);

        if (card.getStatus() == CardStatus.EXPIRED) {
            throw new InvalidCardOperationException("Card is expired");
        }
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

    @Override
    public CardResponseDto blockCard(Long id) {

        Card card = cardRepository.findById(id).
                orElseThrow(() -> new CardNotFoundException("Card not found with id: "+id));

        isCardExpired(card);

        if (card.getStatus() == CardStatus.EXPIRED) {
            throw new InvalidCardOperationException("Card is expired");
        }
       if(card.getStatus()==CardStatus.BLOCKED) {
           throw new CardAlreadyBlockedException("Card already blocked");
       }
       card.setStatus(CardStatus.BLOCKED);
       card.setUpdatedAt(LocalDateTime.now());
       Card savedCard=cardRepository.save(card);

       return cardMapper.toDto(savedCard);
    }


    @Override
    public CardResponseDto unblockCard(Long id){
        Card card = cardRepository.findById(id).
                orElseThrow(() -> new CardNotFoundException("Card not found with id: "+id));


        isCardExpired(card);

        if (card.getStatus() == CardStatus.EXPIRED) {
            throw new InvalidCardOperationException("Card is expired");
        }


        if(card.getStatus()!=CardStatus.BLOCKED){
            throw new InvalidCardOperationException("Only blocked cards can be unblocked");
        }
        card.setStatus(CardStatus.ACTIVE);
        card.setUpdatedAt(LocalDateTime.now());

        Card savedCard=cardRepository.save(card);

        return cardMapper.toDto(savedCard);
    }

    @Override
    public CardResponseDto closeCard(Long id) {

        Card card = cardRepository.findById(id).
                orElseThrow(() -> new CardNotFoundException("Card not found with id: "+id));

        isCardExpired(card);

        if (card.getStatus() == CardStatus.EXPIRED) {
            throw new InvalidCardOperationException("Card is expired");
        }
        if(card.getStatus()==CardStatus.CLOSED){
            throw new CardAlreadyClosedException("Card already closed");
        }
        if(card.getStatus()==CardStatus.BLOCKED){
            throw new InvalidCardOperationException("Blocked card cannot be closed");
        }

        card.setStatus(CardStatus.CLOSED);
        card.setUpdatedAt(LocalDateTime.now());

        Card savedCard=cardRepository.save(card);

        return cardMapper.toDto(savedCard);

    }


    private void isCardExpired(Card card){

        if(card.getExpireDate().isBefore(LocalDate.now())){
            CardStatus status = card.getStatus();

            if (status != CardStatus.CLOSED && status != CardStatus.EXPIRED) {

               card.setStatus(CardStatus.EXPIRED);
               card.setUpdatedAt(LocalDateTime.now());

                cardRepository.save(card);

           }



        }

    }

    @Override
    public CardResponseDto reopenCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card not found with id: " + id));

        isCardExpired(card);

        if (card.getStatus() == CardStatus.EXPIRED) {
            throw new InvalidCardOperationException("Card is expired");
        }

        if (card.getStatus() != CardStatus.CLOSED) {
            throw new InvalidCardOperationException("Only closed cards can be reopened");
        }

        card.setStatus(CardStatus.ACTIVE);
        card.setUpdatedAt(LocalDateTime.now());

        Card savedCard = cardRepository.save(card);

        return cardMapper.toDto(savedCard);
    }

    @Override
    public CardResponseDto deposit(Long id, DepositRequestDto request) {
        Card card = cardRepository.findById(id).
                orElseThrow(()->new CardNotFoundException("Card not found with id"+id));

        isCardExpired(card);

        if (card.getStatus() == CardStatus.EXPIRED) {
            throw new InvalidCardOperationException("Card is expired");
        }

        if (card.getStatus() == CardStatus.CLOSED) {
            throw new InvalidCardOperationException("Card is closed");
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0){
             throw  new InvalidCardOperationException("Amount must be positive");
        }
        card.setBalance(card.getBalance().add(request.getAmount()));

        card.setUpdatedAt(LocalDateTime.now());
        Card savedCard=cardRepository.save(card);

       return cardMapper.toDto(savedCard);

    }

    @Override
    public CardResponseDto withDraw(Long id, WithdrawRequestDto request) {
        Card card = cardRepository.findById(id).
                orElseThrow(()->new CardNotFoundException("Card not found with id"+id));

        isCardExpired(card);

        if (card.getStatus() == CardStatus.EXPIRED) {
            throw new InvalidCardOperationException("Card is expired");
        }

        if (card.getStatus() == CardStatus.CLOSED) {
            throw new InvalidCardOperationException("Card is closed");
        }
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw  new InvalidCardOperationException("Amount must be positive");
        }
        if(request.getAmount().compareTo(card.getBalance())>=0){
            throw new InvalidCardOperationException("Balance cant be low than amount");
        }
        card.setBalance(card.getBalance().subtract(request.getAmount()));
        card.setUpdatedAt(LocalDateTime.now());

        Card savedCard= cardRepository.save(card);
        return cardMapper.toDto(savedCard);

    }

}