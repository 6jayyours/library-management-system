package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.request.AddBookRequest;
import com.example.library.response.AddBookResponse;
import com.example.library.response.DeleteBookResponse;
import com.example.library.response.UpdateBookResponse;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book") // base URL for book related operations
public class BookController {
    private final BookService bookService;


    // constructor injection for the BookService
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * Endpoint to add a new book.
     *
     * @param request The request containing book details to be added.
     * @return ResponseEntity containing the response message for adding a book.
     */
    @PostMapping("/addBook")
    public ResponseEntity<AddBookResponse> addNewBook(@Valid @RequestBody AddBookRequest request) {
        AddBookResponse response = bookService.addNewBook(request);
        return ResponseEntity.ok(response);
    }


    /**
     * Endpoint to fetch all books.
     *
     * @return ResponseEntity containing a list of all books.
     */
    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }


    /**
     * Endpoint to fetch a book by its ID.
     *
     * @param id The ID of the book to fetch.
     * @return ResponseEntity containing the book details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }


    /**
     * Endpoint to update an existing book by ID.
     *
     * @param id      The ID of the book to update.
     * @param request The request containing updated book details.
     * @return ResponseEntity with a success message.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UpdateBookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody AddBookRequest request) {
        UpdateBookResponse response = bookService.updateBook(id, request);
        return ResponseEntity.ok(response);
    }


    /**
     * Endpoint to delete a book by its ID.
     *
     * @param id The ID of the book to delete.
     * @return ResponseEntity containing the response message for deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteBookResponse> deleteBook(@PathVariable Long id) {
        DeleteBookResponse response = bookService.deleteBook(id);
        return ResponseEntity.ok(response);
    }
}
