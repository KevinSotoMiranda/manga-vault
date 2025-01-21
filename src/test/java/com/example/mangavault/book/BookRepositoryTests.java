package com.example.mangavault.book;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.mangavault.series.Series;
import com.example.mangavault.series.SeriesRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTests {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Test 
    public void BookRepository_Save_ReturnSavedBook() {
        Series series = Series.builder()
            .title("Some Series Title")
            .author("James Jones")
            .build();
        
        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
            .title("Some Title")
            .isbn13("1111111111111")
            .volume(1)
            .series_id(seriesSaved.getId())
            .build();
        
        Book bookSaved = this.bookRepository.save(book);

        Assertions.assertThat(bookSaved).isNotNull();
        Assertions.assertThat(bookSaved.getTitle()).isEqualTo(book.getTitle());
        Assertions.assertThat(bookSaved.getIsbn13()).isEqualTo(book.getIsbn13());
        Assertions.assertThat(bookSaved.getVolume()).isEqualTo(book.getVolume());
        Assertions.assertThat(bookSaved.getSeries_id()).isGreaterThan(0);
    }

    @Test
    public void BookRepository_SaveAll_ReturnSavedBooks() {
        Series series = Series.builder()
            .title("Some Other Series")
            .author("John Lagon")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
            .title("First Book")
            .isbn13("1111111111111")
            .volume(1)
            .series_id(seriesSaved.getId())
            .build();


        Book book2 = Book.builder()
            .title("Second Book")
            .isbn13("2222222222222")
            .volume(2)
            .series_id(seriesSaved.getId())
            .build();

        Book book3 = Book.builder()
            .title("Third Book")
            .isbn13("3333333333333")
            .series_id(series.getId())
            .build();

        List<Book> books = List.of(book, book2, book3);
    
        List<Book> booksSaved = this.bookRepository.saveAll(books);

        Assertions.assertThat(booksSaved).isNotNull();
        Assertions.assertThat(booksSaved.size()).isEqualTo(3);

        Assertions.assertThat(booksSaved.get(0).getTitle()).isEqualTo(book.getTitle());
        Assertions.assertThat(booksSaved.get(0).getIsbn13()).isEqualTo(book.getIsbn13());
        Assertions.assertThat(booksSaved.get(0).getVolume()).isEqualTo(book.getVolume());
        Assertions.assertThat(booksSaved.get(0).getSeries_id()).isEqualTo(book.getSeries_id());

        Assertions.assertThat(booksSaved.get(1).getTitle()).isEqualTo(book2.getTitle());
        Assertions.assertThat(booksSaved.get(1).getIsbn13()).isEqualTo(book2.getIsbn13());
        Assertions.assertThat(booksSaved.get(1).getVolume()).isEqualTo(book2.getVolume());
        Assertions.assertThat(booksSaved.get(1).getSeries_id()).isEqualTo(book2.getSeries_id());  
        
        Assertions.assertThat(booksSaved.get(2).getTitle()).isEqualTo(book3.getTitle());
        Assertions.assertThat(booksSaved.get(2).getIsbn13()).isEqualTo(book3.getIsbn13());
        Assertions.assertThat(booksSaved.get(2).getVolume()).isEqualTo(book3.getVolume());
        Assertions.assertThat(booksSaved.get(2).getSeries_id()).isEqualTo(book3.getSeries_id());
    }
    
    @Test
    public void BookRepository_DeleteById_ReturnsEmptyOptional() {
        Series series = Series.builder()
            .title("Adam Joe")
            .author("Adam Joe")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
            .title("Some Title")
            .isbn13("4444444444444")
            .volume(1)
            .series_id(seriesSaved.getId())
            .build();

        Book bookSaved = this.bookRepository.save(book);

        this.bookRepository.deleteById(bookSaved.getId());

        Assertions.assertThat(this.bookRepository.findById(bookSaved.getId())).isEmpty();
    }

    @Test
    public void BookRepository_DeleteAllById_ReturnsEmptyOptional() {
        Series series = Series.builder()
            .title("Series")
            .author("JJ Dansie")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
            .title("One Book")
            .isbn13("1234512345123")
            .volume(1)
            .series_id(seriesSaved.getId())
            .build();

        
        Book book2 = Book.builder()
            .title("Two Book")
            .isbn13("1234512345122")
            .volume(2)
            .series_id(seriesSaved.getId())
            .build();

        Book book3 = Book.builder()
            .title("Three Book")
            .isbn13("1234512345111")
            .volume(3)
            .series_id(seriesSaved.getId())
            .build();


        List<Book> books = List.of(book, book2, book3);

        List<Book> booksSaved = this.bookRepository.saveAll(books);

        List<Long> ids = List.of(
            booksSaved.get(0).getId(),
            booksSaved.get(1).getId(),
            booksSaved.get(2).getId()
        );

        this.bookRepository.deleteAllById(ids);

        Assertions.assertThat(this.bookRepository.findById(ids.get(0))).isEmpty();
        Assertions.assertThat(this.bookRepository.findById(ids.get(1))).isEmpty();
        Assertions.assertThat(this.bookRepository.findById(ids.get(2))).isEmpty();
    }

    @Test
    public void BookRepository_findByIsbn13_ReturnBook() {
        Series series = Series.builder()
            .title("The Series Title")
            .author("Norlan Doe")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
            .title("The First Book")
            .isbn13("1233456789123")
            .volume(1)
            .series_id(seriesSaved.getId())
            .build();
        
        Book bookSaved = this.bookRepository.save(book);

        Optional<Book> bookOptional = this.bookRepository.findByIsbn13(bookSaved.getIsbn13());
        
        Assertions.assertThat(bookOptional).isNotEmpty();

        Assertions.assertThat(bookOptional.get().getTitle()).isEqualTo(bookSaved.getTitle());
        Assertions.assertThat(bookOptional.get().getIsbn13()).isEqualTo(bookSaved.getIsbn13());
        Assertions.assertThat(bookOptional.get().getVolume()).isEqualTo(bookSaved.getVolume());
        Assertions.assertThat(bookOptional.get().getSeries_id()).isEqualTo(bookSaved.getSeries_id());        
    }   
}
