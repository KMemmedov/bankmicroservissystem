package com.bank.customerservice.service.impl;

import com.bank.customerservice.dto.CustomerRequestDto;
import com.bank.customerservice.dto.CustomerResponseDto;
import com.bank.customerservice.entity.Customer;
import com.bank.customerservice.entity.CustomerStatus;
import com.bank.customerservice.exception.CustomerNotFoundException;
import com.bank.customerservice.mapper.CustomerMapper;
import com.bank.customerservice.repository.CustomerRepository;
import com.bank.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
   private final CustomerRepository customerRepository;
   private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper){

        this.customerRepository=customerRepository;
        this.customerMapper = customerMapper;
    }

@Override
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



    return customerMapper.toDto(customer);
    }

    @Override
 public CustomerResponseDto getCustomerById(Long id ){
    Customer customer= customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found this id: "+id));

return customerMapper.toDto(customer);

 }



@Override
public List< CustomerResponseDto> getAllCustomers(){

        List<CustomerResponseDto> customers = customerRepository.findAll().stream().map(c -> customerMapper.toDto(c)).toList();

      return customers;
}
@Override
public CustomerResponseDto updateCustomer(Long id,CustomerRequestDto request){

      Customer customer=customerRepository.findById(id).
                orElseThrow(()->new CustomerNotFoundException("Customer not found with  id: "+id ));
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setPin(request.getPin());

    Customer updatedCustomer=customerRepository.save(customer);
     return customerMapper.toDto(updatedCustomer);

}






}
