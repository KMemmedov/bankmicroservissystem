package com.bank.customerservice.service;

import com.bank.customerservice.dto.CustomerRequestDto;
import com.bank.customerservice.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto request);
    CustomerResponseDto getCustomerById(Long id );
    List< CustomerResponseDto> getAllCustomers();
    CustomerResponseDto updateCustomer(Long id,CustomerRequestDto request);

}
