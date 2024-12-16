package com.librarysystem.library_management.repository;
import com.librarysystem.library_management.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByIsbn(String isbn);
}
