package com.example.mangavault.book;

import jakarta.persistence.*;

@Entity
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

    public Book() {
    }

    public Book(String title, String isbn13, int volume, Long series_id) {
        this.title = title;
        this.isbn13 = isbn13;
        this.volume = volume;
        this.series_id = series_id;
    }

    public Book(String title, String isbn13, int volume, Long series_id, Long id) {
        this.title = title;
        this.isbn13 = isbn13;
        this.volume = volume;
        this.series_id = series_id;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public int getVolume() {
        return volume;
    }

    public Long getSeries_id() {
        return series_id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn_13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setSeries_id(Long series_id) {
        this.series_id = series_id;
    }

    public void setId(Long id) {
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
