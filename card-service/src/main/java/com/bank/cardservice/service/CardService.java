package com.bank.cardservice.service;

import com.bank.cardservice.dto.CardRequestDto;
import com.bank.cardservice.dto.CardResponseDto;
import com.bank.cardservice.dto.UpdateCardRequestDto;

import java.util.List;

public interface CardService {


    public CardResponseDto createCard(CardRequestDto request);

    public List<CardResponseDto> getAllCards();


    public CardResponseDto getCardById(Long id);

    public CardResponseDto updateCard(Long id, UpdateCardRequestDto request);
}
