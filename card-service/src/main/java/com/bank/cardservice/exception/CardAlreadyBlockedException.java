package com.bank.cardservice.exception;

public class CardAlreadyBlockedException extends RuntimeException {
    public CardAlreadyBlockedException(String message) {
        super(message);
    }
}
