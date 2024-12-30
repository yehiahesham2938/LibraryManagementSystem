package com.librarysystem.library_management.controller;

import com.librarysystem.library_management.model.Book;
import com.librarysystem.library_management.model.BorrowedBook;
//import com.librarysystem.library_management.model.ReturnedBook;
import com.librarysystem.library_management.model.User;
import com.librarysystem.library_management.repository.BookRepository;
import com.librarysystem.library_management.repository.BorrowedBookRepository;
//import com.librarysystem.library_management.repository.ReturnedBookRepository;
import com.librarysystem.library_management.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    public String showUserPage(Model m) {
        List<Book> allBooks = bookRepository.findAll();
        for (Book book : allBooks) {
            if (book.getCoverPhoto() != null) {
                String encodedImage = Base64.getEncoder().encodeToString(book.getCoverPhoto());
                book.setEncodedCoverPhoto(encodedImage);
            }
        }
        m.addAttribute("books", allBooks);
        return "User/home";
    }

    @GetMapping("/ViewProfile")
    public String viewProfile() {
        return "User/Profile";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/About")
    public String about() {
        return "User/About";
    }

    @GetMapping("/home")
    public String viewBooks(Model m) {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            if (book.getCoverPhoto() != null) {
                String encodedImage = Base64.getEncoder().encodeToString(book.getCoverPhoto());
                book.setEncodedCoverPhoto(encodedImage);
            }
        }
        m.addAttribute("books", books);
        return "User/home";
    }

    //This method handle borrowing the book, and checks if each book have at least 1 book in stock.
    @PostMapping("/borrow")
    public String borrowBook(@RequestParam("bookId") int bookId, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("userId");
        User user = userRepository.findByUsername(username);
        Book book = bookRepository.findById(bookId).orElse(null);
        BorrowedBook existingBorrowedBook = borrowedBookRepository.findByBookIdAndUserId(bookId, username);
        if (existingBorrowedBook != null) {
            return "redirect:/user/home?error=You have already borrowed this book.";
        }
        if (book.getStock() > 0)
        {
            book.setStock(book.getStock() - 1);
            bookRepository.save(book);
            BorrowedBook borrowedBook = new BorrowedBook();
            borrowedBook.setBookId(bookId);
            borrowedBook.setUserId(user.getUsername());

            borrowedBookRepository.save(borrowedBook);
            return "redirect:/user/home?success=Book borrowed successfully!";
        }
        else
        {
            return "redirect:/user/home?error=Book out of stock.";
        }
    }

    @GetMapping("/borrowedBooks")
    public String viewBorrowedBooks(HttpServletRequest request, Model m) {
        String username = (String) request.getSession().getAttribute("userId");
        List<BorrowedBook> borrowedBooks = borrowedBookRepository.findByUserId(username);
        List<Book> borrowedBookDetails = new ArrayList<>();
        for (BorrowedBook borrowedBook : borrowedBooks)
        {
            Book book = bookRepository.findById(borrowedBook.getBookId()).orElse(null);
            if (book != null)
            {
                String encodedImage = Base64.getEncoder().encodeToString(book.getCoverPhoto());
                book.setEncodedCoverPhoto(encodedImage);
                borrowedBookDetails.add(book);
            }
        }

        m.addAttribute("books", borrowedBookDetails);
        return "User/BorrowedBooks";
    }

//
//    @PostMapping("/return")
//    public String returnBook(@RequestParam("bookId") int bookId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
//
//        String username = (String) request.getSession().getAttribute("userId");
//
//        BorrowedBook borrowedBook = borrowedBookRepository.findByBookIdAndUserId(bookId, username);
//
//        ReturnedBook existingReturnedBook = returnedBookRepository.findByBorrowedBookId(borrowedBook.getId());
//        if (existingReturnedBook != null) {
//            redirectAttributes.addFlashAttribute("error", "This book has already been returned.");
//            return "redirect:/user/borrowedBooks";
//        }
//
//        Book book = bookRepository.findById(bookId).orElse(null);
//        if (book != null) {
//            book.setStock(book.getStock() + 1);
//            bookRepository.save(book);
//        }
//
//        // Create and save the returned book entry
//        ReturnedBook returnedBook = new ReturnedBook();
//        returnedBook.setUserId(username);
//        returnedBook.setReturnDate(LocalDateTime.now());
//        returnedBook.setBorrowedBook(borrowedBook);
//
//        returnedBookRepository.save(returnedBook);
//
//        redirectAttributes.addFlashAttribute("success", "Book returned successfully!");
//        return "User/BorrowedBooks";
//    }

    @PostMapping("/return")
    public String returnBook(@RequestParam("bookId") int bookId, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("userId");

        Book book = bookRepository.findById(bookId).orElse(null);

        book.setStock(book.getStock() + 1);
        bookRepository.save(book);

        BorrowedBook borrowedBook = borrowedBookRepository.findByBookIdAndUserId(bookId, username);
        if (borrowedBook != null) {
            borrowedBookRepository.delete(borrowedBook);
        }

        return "User/BorrowedBooks";
    }

}
