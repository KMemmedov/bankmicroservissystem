package com.bank.cardservice.exception;

public class InvalidCardOperationException extends RuntimeException {


    public InvalidCardOperationException(String message) {
        super(message);
    }
}
