package com.bank.customerservice.controller;

import com.bank.customerservice.dto.CustomerRequestDto;
import com.bank.customerservice.dto.CustomerResponseDto;
import com.bank.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

private final CustomerService customerService;

public CustomerController(CustomerService customerService){

    this.customerService=customerService;
}

@PostMapping
public CustomerResponseDto createCustomer(@Valid @RequestBody CustomerRequestDto request){
  return   customerService.createCustomer(request);
}

@GetMapping("/{id}")
public CustomerResponseDto getCustomerById(@PathVariable Long id){
  return  customerService.getCustomerById(id);
}

@GetMapping()
public List<CustomerResponseDto> getAllCustomers(){

   return customerService.getAllCustomers();

}




}
