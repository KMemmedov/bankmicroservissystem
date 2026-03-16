package com.bank.customerservice.service;

import com.bank.customerservice.dto.CustomerRequestDto;
import com.bank.customerservice.dto.CustomerResponseDto;
import com.bank.customerservice.entity.Customer;
import com.bank.customerservice.entity.CustomerStatus;
import com.bank.customerservice.exception.CustomerNotFoundException;
import com.bank.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
     customer.setStatus(CustomerStatus.ACTIVE);

    customerRepository.save(customer);



    return mapToResponse(customer);
    }
 public CustomerResponseDto getCustomerById(Long id ){
    Customer customer= customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found this id: "+id));

return mapToResponse(customer);

 }


 private CustomerResponseDto mapToResponse(Customer customer){
   CustomerResponseDto response = new CustomerResponseDto();
     response.setFirstName(customer.getFirstName());
     response.setLastName(customer.getLastName());
     response.setEmail(customer.getEmail());
     response.setPhoneNumber(customer.getPhoneNumber());
     response.setStatus(customer.getStatus());
     response.setCreatedAt(customer.getCreatedAt());
     response.setUpdatedAt(customer.getUpdatedAt());

   return response;


 }

public List< CustomerResponseDto> getAllCustomers(){

        List<CustomerResponseDto> customers = customerRepository.findAll().stream().map(c -> mapToResponse(c)).toList();

      return customers;
}








}
