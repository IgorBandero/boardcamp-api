package com.boardcamp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {    

    // Games
    @ExceptionHandler({GameConflictException.class})
    public ResponseEntity<String> handleGameConflict(GameConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }    

    @ExceptionHandler({GameNotFoundException.class})
    public ResponseEntity<String> handleGameNotFound(GameNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({GameOutOfStockException.class})
    public ResponseEntity<String> handleGameOutOfStock(GameOutOfStockException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
    

    // Customers
    @ExceptionHandler({CustomerConflictException.class})
    public ResponseEntity<String> handleCustomerConflict(CustomerConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    // Rentals
    @ExceptionHandler({RentalNotFoundException.class})
    public ResponseEntity<String> handleRentalNotFound(RentalNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({CompletedRentalException.class})
    public ResponseEntity<String> handleCompletedRental(CompletedRentalException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
