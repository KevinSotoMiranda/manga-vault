package com.example.mangavault.series;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "series_sequence")
    @SequenceGenerator(name = "series_sequence", sequenceName = "series_sequence", allocationSize = 1)
    private Long id;

    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;
    @Size(max = 255, message = "Author name must be at most 255 characters")
    private String author;

    public Series() {
    }

    public Series(String title, String author) {
        this.title = title;
        this.author = author;
    } 

    public Series(String title, String author, Long id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Series{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", id=" + id +
                '}';
    }
}
