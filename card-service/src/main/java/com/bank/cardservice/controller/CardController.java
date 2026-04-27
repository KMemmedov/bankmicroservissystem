package com.bank.cardservice.controller;


import com.bank.cardservice.dto.*;
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
   @PatchMapping("/{id}/block")
    public ResponseEntity<CardResponseDto> blockCard(@PathVariable Long id){

       return ResponseEntity.ok(cardService.blockCard(id));

   }
   @PatchMapping("/{id}/unblock")
   public ResponseEntity<CardResponseDto> unblockCard(@PathVariable Long id){


        return ResponseEntity.ok(cardService.unblockCard(id)    );
   }

   @PatchMapping("/{id}/close")
   public ResponseEntity<CardResponseDto> closeCard(@PathVariable Long id){


        return ResponseEntity.ok(cardService.closeCard(id));
   }
    @PatchMapping("/{id}/reopen")
    public ResponseEntity<CardResponseDto> reopenCard(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.reopenCard(id));
    }

    @PatchMapping("/{id}/deposit")
    public ResponseEntity<CardResponseDto>deposit(@PathVariable Long id,@Valid @RequestBody DepositRequestDto request){

        return ResponseEntity.ok(cardService.deposit(id, request));
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<CardResponseDto>withdraw(@PathVariable Long id,@Valid @RequestBody WithdrawRequestDto request){

        return ResponseEntity.ok(cardService.withDraw(id, request));
    }
    @PostMapping("/transfer")
    public ResponseEntity<CardResponseDto>transfer(@Valid @RequestBody TransferRequestDto request){

      return ResponseEntity.ok(cardService.transfer(request));
    }

}
