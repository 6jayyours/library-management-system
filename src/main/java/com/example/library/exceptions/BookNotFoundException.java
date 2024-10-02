package com.example.library.exceptions;

// Custom exception class to handle errors when a book is not found
public class BookNotFoundException extends RuntimeException {

    // Constructor that takes a message and passes it to the superclass (RuntimeException)
    public BookNotFoundException(String message) {
        super(message); // Calls the constructor of RuntimeException with the provided message
    }
}
