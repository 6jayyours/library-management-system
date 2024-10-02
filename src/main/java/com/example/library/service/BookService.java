package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.exceptions.BookAdditionException;
import com.example.library.exceptions.BookNotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.request.AddBookRequest;
import com.example.library.response.AddBookResponse;
import com.example.library.response.DeleteBookResponse;
import com.example.library.response.UpdateBookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    // constructor injection for the BookRepository
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // method to add new book
    public AddBookResponse addNewBook(AddBookRequest request) {
        try {
            Book book = new Book(); // create a new book
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            book.setPublishedDate(request.getPublishedDate());
            bookRepository.save(book); // save book
            return new AddBookResponse("Book added successfully"); // success response
        } catch (Exception e) {
            // throw custom exception for adding new book
            throw new BookAdditionException("Error occurred while adding the book: " + e.getMessage());
        }
    }


    // method to fetch all books from repository
    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll(); // fetch and return all books
        } catch (Exception e) {
            // throw generic runtime exception
            throw new RuntimeException("An error occurred while fetching books: " + e.getMessage());
        }
    }


    // method to retrieve a specific book by its ID
    public Book getBookById(Long id) {
        try {

            // fetch book by its ID or throw custom exception if book not found
            return bookRepository.findById(id)
                    .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        } catch (BookNotFoundException e) {
            throw e; // pass exception to method that called it
        } catch (Exception e) {
            // throw generic runtime exception
            throw new RuntimeException("An error occurred while fetching the book: " + e.getMessage());
        }
    }

    // method to update an existing book details by its ID
    public UpdateBookResponse updateBook(Long id, AddBookRequest request) {
        try {
            // find book by id or throw custom exception if book not found
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));

            // update book details
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            book.setPublishedDate(request.getPublishedDate());
            bookRepository.save(book); // save updated book

            return new UpdateBookResponse("Book updated successfully");
        }  catch (BookNotFoundException e) {
            throw e; // pass exception to method that called it
        } catch (Exception e) {

            // throw generic runtime exception
            throw new RuntimeException("Error occurred while updating the book: " + e.getMessage());
        }
    }


    // method to delete a book by its ID
    public DeleteBookResponse deleteBook(Long id) {
        try {

            // check if the book exists or throw custom exception if book not found
            if (!bookRepository.existsById(id)) {
                throw new BookNotFoundException("Book not found with ID: " + id);
            }
            bookRepository.deleteById(id); // deleting the book
            return new DeleteBookResponse("Book deleted successfully"); // return success response
        } catch (BookNotFoundException e) {
            throw e; // pass exception to method that called it
        } catch (Exception e) {
            // throw generic runtime exception
            throw new RuntimeException("Error occurred while deleting the book: " + e.getMessage());
        }
    }
}

