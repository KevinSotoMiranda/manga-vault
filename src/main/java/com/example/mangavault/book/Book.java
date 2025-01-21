package com.example.mangavault.book;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    private Long id;

    private String title;

    private String isbn13;

    private int volume;

    private Long series_id;

    public Book(String title, String isbn13, int volume, Long series_id) {
        this.title = title;
        this.isbn13 = isbn13;
        this.volume = volume;
        this.series_id = series_id;
    }

    public Book(Long id, String title, String isbn13, int volume, Long series_id) {
        this.title = title;
        this.isbn13 = isbn13;
        this.volume = volume;
        this.series_id = series_id;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", volume=" + volume +
                ", series_id=" + series_id +
                ", id=" + id +
                '}';
    }
}
