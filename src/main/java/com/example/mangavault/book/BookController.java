package com.example.mangavault.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(path = "api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book bookCreated = this.bookService.createBook(book);        
        return new ResponseEntity<>(bookCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = this.bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping(path = "{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        this.bookService.updateBook(bookId, book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        this.bookService.deleteBook(bookId);
    }
}
