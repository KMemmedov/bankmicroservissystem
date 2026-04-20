package com.bank.cardservice.dto;

import com.bank.cardservice.enums.CardType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CardRequestDto {
    @NotNull(message = "CustomerId is required")
    private Long customerId;

    @NotNull(message = "CardType is required")
    private CardType cardType;

    @PositiveOrZero(message = "Credit limit cannot be negative")
    private BigDecimal creditLimit;
}
