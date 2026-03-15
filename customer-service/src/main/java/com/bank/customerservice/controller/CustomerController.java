package com.bank.customerservice.controller;

import com.bank.customerservice.dto.CustomerRequestDto;
import com.bank.customerservice.dto.CustomerResponseDto;
import com.bank.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

private final CustomerService customerService;

public CustomerController(CustomerService customerService){

    this.customerService=customerService;
}

@PostMapping
public CustomerResponseDto createCustomer(@RequestBody CustomerRequestDto request){
  return   customerService.createCustomer(request);
}










}
