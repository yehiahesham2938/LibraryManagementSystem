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

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("")
    public String showUserPage()
    {
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



}



