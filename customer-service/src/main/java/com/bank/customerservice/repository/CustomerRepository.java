package com.bank.customerservice.repository;

import com.bank.customerservice.entity.Customer;
import com.bank.customerservice.entity.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long>{


    @Query("select c from Customer c where c.status = :status")
List<Customer> findActiveCustomers(@Param("status")CustomerStatus status);
















}
