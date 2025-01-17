package com.example.mangavault.collection;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collection_sequence")
    @SequenceGenerator(name = "collection_sequence", sequenceName = "collection_sequence", allocationSize = 1)
    @Column(name = "collection_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

    public Collection(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    } 

    private Collection(Long id, Long userId, Long bookId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                "userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}