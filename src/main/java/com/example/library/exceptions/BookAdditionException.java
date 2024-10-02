package com.example.library.exceptions;

// Custom exception class to handle errors related to book addition
public class BookAdditionException extends RuntimeException {

    // Constructor that takes a message and passes it to the superclass (RuntimeException)
    public BookAdditionException(String message) {
        super(message); // Calls the constructor of RuntimeException with the provided message
    }
}