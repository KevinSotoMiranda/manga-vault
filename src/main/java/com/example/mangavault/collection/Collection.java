package com.example.mangavault.collection;

import jakarta.persistence.*;

@Entity
@Table(name = "collections")
public class Collection {
    private Long userId;
    private Long bookId;

    public Collection(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return "Collection{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
