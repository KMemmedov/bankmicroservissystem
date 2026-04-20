package com.bank.cardservice.exception;

public class CustomerCardLimitExceededException extends RuntimeException{


    public CustomerCardLimitExceededException(String message){
        super(message);
    }
}
