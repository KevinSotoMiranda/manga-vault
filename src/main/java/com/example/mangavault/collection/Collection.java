package com.example.mangavault.collection;

import jakarta.persistence.*;

@Entity
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collection_sequence")
    @SequenceGenerator(name = "collection_sequence", sequenceName = "collection_sequence", allocationSize = 1)
    private Long id;
    private Long userId;
    private Long bookId;

    public Collection() {

    }

    public Collection(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    } 

    public Collection(Long id, Long userId, Long bookId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getId() {
        return this.id;
    } 

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    
    public void setId(Long id) {
        this.id = id;
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
