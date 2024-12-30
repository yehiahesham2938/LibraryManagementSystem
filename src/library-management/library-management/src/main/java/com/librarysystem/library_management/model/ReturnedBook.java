//package com.librarysystem.library_management.model;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "ReturnedBooks")
//public class ReturnedBook {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @OneToOne
//    @JoinColumn(name = "borrowed_book_id", referencedColumnName = "id")
//    private BorrowedBook borrowedBook;
//
//    @Column(name = "user_id", nullable = false)
//    private String userId;
//
//    @Column(name = "return_date", nullable = false)
//    private LocalDateTime returnDate = LocalDateTime.now();
//
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public BorrowedBook getBorrowedBook() {
//        return borrowedBook;
//    }
//
//    public void setBorrowedBook(BorrowedBook borrowedBook) {
//        this.borrowedBook = borrowedBook;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public LocalDateTime getReturnDate() {
//        return returnDate;
//    }
//
//    public void setReturnDate(LocalDateTime returnDate) {
//        this.returnDate = returnDate;
//    }
//}
