package com.bank.cardservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class ApiErrorResponse {


    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private String message;










}
