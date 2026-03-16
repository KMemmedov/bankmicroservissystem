package com.bank.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomerRequestDto {
   @NotBlank(message = "First name is required")
   private String firstName;

   @NotBlank(message = "Last name is required")
   private String lastName;

   @Email(message = "Email format is invalid")
   @NotBlank(message = "Email  is required")
   private String email;

   @NotBlank(message = "Phone number is required")
   private String phoneNumber;

   @NotBlank(message = "Pin  is required")
   private String pin;








}
