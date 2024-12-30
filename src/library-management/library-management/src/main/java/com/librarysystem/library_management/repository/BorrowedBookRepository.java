package com.librarysystem.library_management.repository;

import com.librarysystem.library_management.model.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
    List<BorrowedBook> findByUserId(String userId);

    BorrowedBook findByBookIdAndUserId(int bookId, String username);
}
