package com.librarysystem.library_management.controller;
import com.librarysystem.library_management.model.Book;
import com.librarysystem.library_management.model.BorrowedBook;
import com.librarysystem.library_management.model.User;
import com.librarysystem.library_management.repository.BookRepository;
import com.librarysystem.library_management.repository.BorrowedBookRepository;
import com.librarysystem.library_management.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
    public String showUserPage(Model m)
    {
        List<Book> allbooks = bookRepository.findAll();

        for (Book book : allbooks) {
            if (book.getCoverPhoto() != null) {
                String encodedImage = Base64.getEncoder().encodeToString(book.getCoverPhoto());
                book.setEncodedCoverPhoto(encodedImage);
            }
        }

        m.addAttribute("books", allbooks);
        return "User/home";

    }

//    @GetMapping("/home")
//    public String ViewBooks(){
//        return "User/home";
//    }

    @GetMapping("/ViewProfile")
    public String ViewProfile(){
        return "User/ViewProfile";
    }

    @GetMapping("/About")
    public String About(){
        return "User/About";
    }
    @GetMapping("/ContactUs")
    public String ContactUs(){
        return "User/ContactUs";
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

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam("bookId") int bookId, HttpServletRequest request) {

        String username = (String) request.getSession().getAttribute("userId");
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return "redirect:/login?error=User not found.";
        }
        Book book = bookRepository.findById(bookId).orElse(null);

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






}



