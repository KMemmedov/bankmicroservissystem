package com.bank.cardservice.service;

import com.bank.cardservice.dto.CardRequestDto;
import com.bank.cardservice.dto.CardResponseDto;

public interface CardService {


    public CardResponseDto createCard(CardRequestDto request);



}
