package com.example.mangavault.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.mangavault.exception.ApiRequestException;
import com.example.mangavault.series.Series;
import com.example.mangavault.series.SeriesRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final SeriesRepository seriesRepository;

    @Autowired
    public BookService(BookRepository bookRepository, SeriesRepository seriesRepository) {
        this.bookRepository = bookRepository;
        this.seriesRepository = seriesRepository;
    }

    public Book createBook(Book book) {
        Optional<Book> bookOptional = this.bookRepository.findByIsbn13(book.getIsbn13());
        
        if(bookOptional.isPresent()) {
            throw new ApiRequestException("Book already exists", HttpStatus.CONFLICT); 
        }

        Optional<Series> seriesOptional = this.seriesRepository.findById(book.getSeries_id());
        
        if(!seriesOptional.isPresent()) {
            throw new ApiRequestException("Series does not exist", HttpStatus.NOT_FOUND);
        }   

        return this.bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public Book updateBook(Long bookId, Book book) {
        Optional<Book> optionalBook = this.bookRepository.findById(bookId);

        if(!optionalBook.isPresent()) {
            throw new ApiRequestException(String.format("Book does not exist with id: %d", bookId), HttpStatus.NOT_FOUND); 
        }

        Optional<Series> optionalSeries = this.seriesRepository.findById(book.getSeries_id());

        if(!optionalSeries.isPresent()) {
            throw new ApiRequestException(String.format("Series does not exist with id: %d", book.getSeries_id()), HttpStatus.NOT_FOUND);
        } 

        return this.bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        Optional<Book> optionalBook = this.bookRepository.findById(bookId);

        if(!optionalBook.isPresent()) {
            throw new ApiRequestException(String.format("Book does not exist with id: %d", bookId), HttpStatus.NOT_FOUND);
        }

        this.bookRepository.deleteById(bookId);
    }

}
