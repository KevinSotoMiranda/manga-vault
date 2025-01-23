package com.example.mangavault.collection;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.mangavault.book.Book;
import com.example.mangavault.book.BookRepository;
import com.example.mangavault.series.Series;
import com.example.mangavault.series.SeriesRepository;
import com.example.mangavault.user.User;
import com.example.mangavault.user.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CollectionRepositoryTests {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Test
    public void CollectionRepository_Save_ReturnSavedCollection() {
        User user = User.builder()
            .username("null")
            .build();

        User userSaved = this.userRepository.save(user);

        Series series = Series.builder()
            .title("The Series Name")
            .author("The Author")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
            .title("Some Title")
            .isbn13("1111111111111")
            .volume(1)
            .series_id(seriesSaved.getId())
            .build();

        Book bookSaved = this.bookRepository.save(book);

        Collection collection = Collection.builder()
            .userId(userSaved.getId())
            .bookId(bookSaved.getId())
            .build();

        Collection collectionSaved = this.collectionRepository.save(collection);

        Assertions.assertThat(collectionSaved).isNotNull();
        Assertions.assertThat(collectionSaved.getId()).isGreaterThan(0);
        Assertions.assertThat(collectionSaved.getUserId()).isEqualTo(userSaved.getId());
        Assertions.assertThat(collectionSaved.getBookId()).isEqualTo(bookSaved.getId());
    }
    
    @Test
    public void CollectionRepository_SaveAll_ReturnsSavedCollections() {
        User user = User.builder()
            .username("FirstOne")
            .build();

        User userSaved = this.userRepository.save(user);

        User user2 = User.builder()
            .username("SeconOne")
            .build();
        
        User userSaved2 = this.userRepository.save(user2);

        User user3 = User.builder()
            .username("ThirdOne")
            .build();

        User userSaved3 = this.userRepository.save(user3);

        Series series = Series.builder()
            .title("Some Title")
            .author("Some Author")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
            .title("The Book Title")
            .isbn13("1234512345555")
            .series_id(seriesSaved.getId())
            .volume(1)
            .build();
        
        Book book2 = Book.builder()
        .title("The Book Title Two")
        .isbn13("1234512345595")
        .series_id(seriesSaved.getId())
        .volume(2)
        .build();

        Book book3 = Book.builder()
        .title("The Book Title Three")
        .isbn13("1234912345555")
        .series_id(seriesSaved.getId())
        .volume(3)
        .build();

        List<Book> booksSaved = this.bookRepository.saveAll(List.of(book, book2, book3));

        Collection collection = Collection.builder()
            .userId(userSaved.getId())
            .bookId(booksSaved.get(0).getId())
            .build();

        Collection collection2 = Collection.builder()
            .userId(userSaved2.getId())
            .bookId(booksSaved.get(1).getId())
            .build();

        Collection collection3 = Collection.builder()
            .userId(userSaved3.getId())
            .bookId(booksSaved.get(2).getId())
            .build();

        List<Collection> collections = List.of(collection, collection2, collection3);

        List<Collection> collectionSaved = this.collectionRepository.saveAll(collections);

        Assertions.assertThat(collectionSaved).isNotEmpty();
        Assertions.assertThat(collectionSaved.size()).isEqualTo(3);

        Assertions.assertThat(collectionSaved.get(0).getUserId()).isEqualTo(userSaved.getId());
        Assertions.assertThat(collectionSaved.get(0).getBookId()).isEqualTo(book.getId());


        Assertions.assertThat(collectionSaved.get(1).getUserId()).isEqualTo(userSaved2.getId());
        Assertions.assertThat(collectionSaved.get(1).getBookId()).isEqualTo(book2.getId());


        Assertions.assertThat(collectionSaved.get(2).getUserId()).isEqualTo(userSaved3.getId());
        Assertions.assertThat(collectionSaved.get(2).getBookId()).isEqualTo(book3.getId());    
    }

    @Test
    public void CollectionRepository_DeleteById_ReturnsEmptyOptional() {
        User user = User.builder()
            .username("Theusername")
            .build();

        User userSaved = this.userRepository.save(user);
        
        Series series = Series.builder()
        .author("Some Author")
        .title("Some Title")
        .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
        .isbn13("1233409876121")
        .title("The best book")
        .volume(1)
        .series_id(seriesSaved.getId())
        .build();

        Book bookSaved = this.bookRepository.save(book);

        Collection collection = Collection.builder()
        .bookId(bookSaved.getId())
        .userId(userSaved.getId())
        .build();

        Collection collectionSaved = this.collectionRepository.save(collection);

        this.collectionRepository.deleteById(collectionSaved.getId());

        Assertions.assertThat(this.collectionRepository.findById(collectionSaved.getId())).isEmpty();
    }

    @Test
    public void CollectionRepository_DeleteAllById_ReturnsEmptyOptional() {
        User user = User.builder()
            .username("First")
            .build();

        User user2 = User.builder()
            .username("Second")
            .build();

        User user3 = User.builder()
            .username("Third")
            .build();
        
        List<User> usersSaved = this.userRepository.saveAll(List.of(user, user2, user3));

        Series series = Series.builder()
            .author("Soem Author")
            .title("The T")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
            .title("The title")
            .isbn13("0000099999888")
            .series_id(seriesSaved.getId())
            .volume(1)
            .build();
        
        Book bookSaved = this.bookRepository.save(book);

        Collection collection = Collection.builder()
            .userId(usersSaved.get(0).getId())
            .bookId(bookSaved.getId())
            .build();

        Collection collectionTwo = Collection.builder()
            .userId(usersSaved.get(1).getId())
            .bookId(bookSaved.getId())
            .build();

        Collection collectionThree = Collection.builder()
            .userId(usersSaved.get(2).getId())
            .bookId(bookSaved.getId())
            .build();
        
        List<Collection> collectionsSaved = this.collectionRepository.saveAll(List.of(collection, collectionTwo, collectionThree));

        List<Long> ids = List.of(collectionsSaved.get(0).getId(), collectionsSaved.get(1).getId(), collectionsSaved.get(2).getId());

        this.collectionRepository.deleteAllById(ids);

        Assertions.assertThat(this.collectionRepository.findById(ids.get(0))).isEmpty();
        Assertions.assertThat(this.collectionRepository.findById(ids.get(1))).isEmpty();
        Assertions.assertThat(this.collectionRepository.findById(ids.get(2))).isEmpty();

    }

    @Test
    public void CollectionRepository_FindByUserIdAndBookId_ReturnCollection() {
        User user = User.builder()
            .username("SomeRandomUserName")
            .build();

        User userSaved = this.userRepository.save(user);

        Series series = Series.builder()
            .author("The Author")
            .title("Some Series Title")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Book book = Book.builder()
            .title("The Title of The Book")
            .isbn13("1234544321121")
            .series_id(seriesSaved.getId())
            .volume(1)
            .build();

        Book bookSaved = this.bookRepository.save(book);

        Collection collection = Collection.builder()
            .userId(userSaved.getId())
            .bookId(bookSaved.getId())
            .build();

        Collection collectionSaved = this.collectionRepository.save(collection);

        Optional<Collection> collectionOptional = this.collectionRepository.findByUserIdAndBookId(userSaved.getId(), bookSaved.getId());
        
        Assertions.assertThat(collectionOptional).isNotEmpty();
        Assertions.assertThat(collectionOptional.get().getBookId()).isEqualTo(collectionSaved.getBookId());
        Assertions.assertThat(collectionOptional.get().getId()).isEqualTo(collectionSaved.getId());
        Assertions.assertThat(collectionOptional.get().getUserId()).isEqualTo(collectionSaved.getUserId());
    }
}
