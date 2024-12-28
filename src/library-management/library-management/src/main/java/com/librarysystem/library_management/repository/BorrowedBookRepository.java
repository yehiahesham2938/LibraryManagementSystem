package com.librarysystem.library_management.repository;

import com.librarysystem.library_management.model.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
}
