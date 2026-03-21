package com.bank.customerservice.controller;

import com.bank.customerservice.dto.CustomerRequestDto;
import com.bank.customerservice.dto.CustomerResponseDto;
import com.bank.customerservice.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

private final CustomerServiceImpl customerService;

public CustomerController(CustomerServiceImpl customerService){

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
@PutMapping("/{id}")
public CustomerResponseDto updateCustomer (@PathVariable  Long id,@Valid @RequestBody CustomerRequestDto request){

return customerService.updateCustomer(id, request);
}

@DeleteMapping("/{id}")
public void deleteCustomer(@PathVariable Long id){

    customerService.deleteCustomer(id);

}



}
