package com.example.mangavault.book;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
    private String isbn_13;
    private int volume;
    private Long series_id;

    public Book(String title, String author, String isbn_13, int volume, Long series_id, Long id) {
        this.title = title;
        this.author = author;
        this.isbn_13 = isbn_13;
        this.volume = volume;
        this.series_id = series_id;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn_13() {
        return isbn_13;
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

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn_13(String isbn_13) {
        this.isbn_13 = isbn_13;
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
                ", author='" + author + '\'' +
                ", isbn_13='" + isbn_13 + '\'' +
                ", volume=" + volume +
                ", series_id=" + series_id +
                ", id=" + id +
                '}';
    }
}
