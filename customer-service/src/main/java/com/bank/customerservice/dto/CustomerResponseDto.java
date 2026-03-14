package com.bank.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomerResponseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;





}
