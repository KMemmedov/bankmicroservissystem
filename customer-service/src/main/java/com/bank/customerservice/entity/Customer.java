package com.bank.customerservice.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


   @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="email",nullable = false,unique = true)
    private String email;


    @Column(name="phone_number",nullable = false,unique = true)
    private String phoneNumber;

    @Column(name="pin",nullable = false)
    private String pin;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private CustomerStatus status;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;







}
