package com.librarysystem.library_management.controller;

import com.librarysystem.library_management.model.Book;
import com.librarysystem.library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/view-books")
    public String viewBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Admin/viewBooks";
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
