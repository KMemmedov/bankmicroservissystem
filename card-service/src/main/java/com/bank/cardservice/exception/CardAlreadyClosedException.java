package com.bank.cardservice.exception;

public class CardAlreadyClosedException extends RuntimeException {
    public CardAlreadyClosedException(String message) {
        super(message);
    }
}
