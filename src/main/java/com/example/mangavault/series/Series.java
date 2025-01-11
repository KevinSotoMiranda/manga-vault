package com.example.mangavault.series;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "series_sequence")
    @SequenceGenerator(name = "series_sequence", sequenceName = "series_sequence", allocationSize = 1)
    @Column(name = "series_id")
    private Long id;

    @Size(max = 255, message = "Title must be at most 255 characters")
    @Column(name = "title")
    @Setter
    private String title;

    @Size(max = 255, message = "Author name must be at most 255 characters")
    @Column(name = "author")
    @Setter
    private String author;

    public Series(String title, String author) {
        this.title = title;
        this.author = author;
    } 

    private Series(Long id, String title, String author) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Series{" +
                "id=" + id + 
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
