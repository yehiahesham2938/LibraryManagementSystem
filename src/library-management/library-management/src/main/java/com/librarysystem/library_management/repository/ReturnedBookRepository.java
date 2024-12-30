//package com.librarysystem.library_management.repository;
//
//import com.librarysystem.library_management.model.ReturnedBook;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface ReturnedBookRepository extends JpaRepository<ReturnedBook, Integer> {
//    ReturnedBook findByBorrowedBookId(int borrowedBookId);
//    List<ReturnedBook> findByUserId(String userId);
//
//    ReturnedBook findByBorrowedBookIdAndUserId(int id, String username);
//}
