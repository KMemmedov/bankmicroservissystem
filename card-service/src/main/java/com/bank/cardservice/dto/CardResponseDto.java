package com.bank.cardservice.dto;

import com.bank.cardservice.enums.CardStatus;
import com.bank.cardservice.enums.CardType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CardResponseDto {


    private Long id;
    private String cardNumber;
    private Long customerId;
    private CardType cardType;
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private CardStatus status;
    private LocalDate expiryDate;
    private LocalDateTime createdAt;






}
