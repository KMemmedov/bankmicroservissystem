package com.bank.cardservice.dto;

import com.bank.cardservice.enums.CardStatus;
import com.bank.cardservice.enums.CardType;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCardRequestDto {


     @Future(message = "Expire date must be in the future")
     private LocalDate expireDate;

     @PositiveOrZero(message = "Credit limit cannot be negative")
     private BigDecimal creditLimit;




}
