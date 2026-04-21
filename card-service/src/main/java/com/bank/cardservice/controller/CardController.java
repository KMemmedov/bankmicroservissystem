package com.bank.cardservice.controller;


import com.bank.cardservice.dto.CardRequestDto;
import com.bank.cardservice.dto.CardResponseDto;
import com.bank.cardservice.dto.UpdateCardRequestDto;
import com.bank.cardservice.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor


@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponseDto> createCard(@Valid @RequestBody CardRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.createCard(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CardResponseDto>> getAllCards (){

        return ResponseEntity.ok(cardService.getAllCards());
    }


    @GetMapping("/{id}")
   public ResponseEntity<CardResponseDto> getCardById(@PathVariable Long id){

    return ResponseEntity.ok(cardService.getCardById(id));

   }

   @PatchMapping("/{id}")
   public ResponseEntity<CardResponseDto> updateCard(@PathVariable Long id ,@RequestBody @Valid UpdateCardRequestDto request){

       return  ResponseEntity.ok(cardService.updateCard(id,request));
   }

}
