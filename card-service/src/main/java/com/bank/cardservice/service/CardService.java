package com.bank.cardservice.service;

import com.bank.cardservice.dto.*;

import java.util.List;

public interface CardService {


    public CardResponseDto createCard(CardRequestDto request);

    public List<CardResponseDto> getAllCards();


    public CardResponseDto getCardById(Long id);

    public CardResponseDto updateCard(Long id, UpdateCardRequestDto request);

    public CardResponseDto blockCard(Long id);

    public CardResponseDto unblockCard(Long id);

    public CardResponseDto closeCard(Long id);

    public CardResponseDto reopenCard(Long id);

    public CardResponseDto deposit(Long id, DepositRequestDto request);

    public CardResponseDto withDraw(Long id, WithdrawRequestDto request);

    public CardResponseDto transfer(TransferRequestDto request);

    public List<CardResponseDto> getCardsByCustomerId(Long customerId);
}
