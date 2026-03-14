package com.bank.customerservice.service;

import com.bank.customerservice.dto.CustomerRequestDto;
import com.bank.customerservice.dto.CustomerResponseDto;
import com.bank.customerservice.entity.Customer;
import com.bank.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {
   private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }


    public CustomerResponseDto createCustomer(CustomerRequestDto request){
     Customer customer = new Customer();
     customer.setFirstName(request.getFirstName());
     customer.setLastName(request.getLastName());
     customer.setEmail(request.getEmail());
     customer.setPhoneNumber(request.getPhoneNumber());
     customer.setPin(request.getPin());
     customer.setCreatedAt(LocalDateTime.now());
     customer.setUpdatedAt(LocalDateTime.now());
     customer.setStatus("ACTIVE");

    Customer savedCustomer= customerRepository.save(customer);
    CustomerResponseDto response=new CustomerResponseDto();

    response.setFirstName(savedCustomer.getFirstName());
    response.setLastName(savedCustomer.getLastName());
    response.setEmail(savedCustomer.getEmail());
    response.setPhoneNumber(savedCustomer.getPhoneNumber());
    response.setStatus(savedCustomer.getStatus());
    response.setCreatedAt(savedCustomer.getCreatedAt());
    response.setUpdatedAt(savedCustomer.getUpdatedAt());


    return response;
    }











}
