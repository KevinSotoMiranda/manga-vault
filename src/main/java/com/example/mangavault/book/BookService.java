package com.example.mangavault.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.mangavault.series.Series;
import com.example.mangavault.series.SeriesRepository;

public class BookService {

    private final BookRepository bookRepository;
    private final SeriesRepository seriesRepository;

    @Autowired
    public BookService(BookRepository bookRepository, SeriesRepository seriesRepository) {
        this.bookRepository = bookRepository;
        this.seriesRepository = seriesRepository;
    }

    public Book createBook(Book book) {
        Optional<Book> bookOptional = this.bookRepository.findByIsbn_13(book.getIsbn_13());
        
        if(bookOptional.isPresent()) {
            throw new IllegalStateException("Book already exists"); // TODO: Create custom exceptions
        }

        Optional<Series> seriesOptional = this.seriesRepository.findById(book.getSeries_id());
        
        if(!seriesOptional.isPresent()) {
            throw new IllegalStateException("Series does not exist"); // TODO: Create custom exceptions
        }   

        return this.bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public Book updateBook(Long bookId, Book book) {
        Optional<Book> optionalBook = this.bookRepository.findById(bookId);

        if(!optionalBook.isPresent()) {
            throw new IllegalStateException(String.format("Book does not exist with id: %b", bookId)); // TODO: Create custom exceptions
        }

        Optional<Series> optionalSeries = this.seriesRepository.findById(book.getSeries_id());

        if(!optionalSeries.isPresent()) {
            throw new IllegalStateException(String.format("Series does not exist with id: %b", book.getSeries_id()));// TODO: Create custom exceptions
        } 

        return this.bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        Optional<Book> optionalBook = this.bookRepository.findById(bookId);

        if(!optionalBook.isPresent()) {
            throw new IllegalStateException(String.format("Book does not exist with id: %b", bookId));// TODO: Create custom exceptions
        }

        this.bookRepository.deleteById(bookId);
    }

}
