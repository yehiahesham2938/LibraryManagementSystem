package com.librarysystem.library_management.controller;

import com.librarysystem.library_management.model.Book;
import com.librarysystem.library_management.repository.BookRepository;
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

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("")
    public String showAdminPage(Model model) {
        return "Admin/admin";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "Admin/addBook";
    }




//    @GetMapping("/view-books")
//    public String viewBooks(Model model) {
//        List<Book> books = bookRepository.findAll();
//        model.addAttribute("books", books);
//        return "Admin/viewBooks";
//    }

    @GetMapping("/view-books")
    public String viewBooks(Model model) {
        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            if (book.getCoverPhoto() != null) {
                String encodedImage = Base64.getEncoder().encodeToString(book.getCoverPhoto());
                book.setEncodedCoverPhoto(encodedImage);
            }
        }

        model.addAttribute("books", books);
        return "Admin/viewBooks";
    }



//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request) {
//        request.getSession().invalidate();
//        return "login";
//    }

@GetMapping("/system-logs")
public String ShowSystemLogs() {
    return "Admin/SystemLogs";
}





    @PostMapping("/create-book")
    public String addBook(
            @ModelAttribute Book book,
            @RequestParam("coverPhotoFile") MultipartFile coverPhotoFile) throws IOException {
        Book existingBook = bookRepository.findByIsbn(book.getIsbn());
        if (existingBook != null) {
            return "error";
        }
        book.setCoverPhoto(coverPhotoFile.getBytes());
        bookRepository.save(book);
        return "Admin/addBook";
    }




}
