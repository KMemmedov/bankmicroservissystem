package com.bank.cardservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter

public class TransferRequestDto {


    @NotNull(message = "Id required")
    private Long fromCardId;
    @NotNull(message = "Id required")
    private Long toCardId;
    @Positive(message = "Amount must be positive ")
    @NotNull(message = "Amount is required")
    private BigDecimal amount;



}
