package com.librarysystem.library_management.model;

import jakarta.persistence.*;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Column(name = "book_name", nullable = false)
    private String bookName;
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Column(name = "author_name", nullable = false)
    private String authorName;
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    @Column(name = "publish_date")
    private LocalDate publishDate;

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Column(name = "isbn", unique = true, length = 13)
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Lob
    @Column(name = "cover_photo")
    private byte[] coverPhoto;
    private String encodedCoverPhoto;

    public byte[] getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(byte[] coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
    public String getEncodedCoverPhoto() {
        return encodedCoverPhoto;
    }

    public void setEncodedCoverPhoto(String encodedCoverPhoto) {
        this.encodedCoverPhoto = encodedCoverPhoto;
    }

    @Column(name = "stock", nullable = false)
    private Integer stock;
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
