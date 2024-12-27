package com.example.mangavault.series;

import jakarta.persistence.*;


@Entity
@Table(name = "series")
public class Series {
    @Id
    private Long id;
    private String title;
    private String author;


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
