package com.bank.cardservice.entity;

import com.bank.cardservice.enums.CardStatus;
import com.bank.cardservice.enums.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cards")
@Builder

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number",nullable = false,unique = true,length = 16)
    private String cardNumber;

    @Column(name = "customer_id",nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type",nullable = false)
    private CardType cardType;

    @Column(name = "balance",precision = 19,scale = 2)
    private BigDecimal balance;

    @Column(name = "credit_limit",precision = 19,scale = 2)
    private BigDecimal creditLimit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardStatus status;

    @Column(name = "expire_date",nullable = false)
    private LocalDate expireDate;

    @Column(name = "cvv",nullable = false,length = 3)
    private String cvv;

    @Column(name = "createdAt",nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updatedAt",nullable = false)
    private LocalDateTime updatedAt;



}
