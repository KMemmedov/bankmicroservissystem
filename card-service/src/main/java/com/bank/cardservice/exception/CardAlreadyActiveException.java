package com.bank.cardservice.exception;

public class CardAlreadyActiveException extends RuntimeException {
    public CardAlreadyActiveException(String message) {
        super(message);
    }
}
