package com.bank.customerservice.mapper;

import com.bank.customerservice.dto.CustomerResponseDto;
import com.bank.customerservice.entity.Customer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerMapper {



    CustomerResponseDto toDto(Customer customer);



}
