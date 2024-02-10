package com.boardcamp.api.exceptions;

public class CompletedRentalException extends RuntimeException {
    public CompletedRentalException(String message){
        super(message);
    }
}
