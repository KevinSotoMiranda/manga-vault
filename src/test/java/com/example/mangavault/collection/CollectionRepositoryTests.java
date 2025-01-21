package com.example.mangavault.collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.mangavault.book.Book;
import com.example.mangavault.book.BookRepository;
import com.example.mangavault.user.User;
import com.example.mangavault.user.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CollectionRepositoryTests {
    @Autowired
    private CollectionRepository collectionRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Test
    public void CollectionRepository_Save_ReturnSavedCollection() {
        User user = User.builder()
            .username("null")
            .build();

        Book book = Book.builder()
            .isbn13("1111111111111")
            .title("Some Title")
            .build();

        Collection collection = Collection.builder()
            .userId(user.getId()).build();

    }
    
}
