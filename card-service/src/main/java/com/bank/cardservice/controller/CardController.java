package com.bank.cardservice.controller;


import com.bank.cardservice.dto.CardRequestDto;
import com.bank.cardservice.dto.CardResponseDto;
import com.bank.cardservice.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor


@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponseDto> createCard(@Valid @RequestBody CardRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.createCard(request));
    }
}
