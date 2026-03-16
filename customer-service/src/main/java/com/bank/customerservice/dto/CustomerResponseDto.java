package com.bank.customerservice.dto;

import com.bank.customerservice.entity.CustomerStatus;
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

    private CustomerStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;





}
