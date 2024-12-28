package com.librarysystem.library_management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "BorrowedBooks")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "book_id", nullable = false)
    private int bookId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "borrow_date", nullable = false)
    private LocalDateTime borrowDate = LocalDateTime.now();

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }
}
