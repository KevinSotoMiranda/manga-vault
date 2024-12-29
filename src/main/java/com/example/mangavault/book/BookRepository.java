package com.example.mangavault.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{

    Optional<Book> findByIsbn13(String isbn13);
}
