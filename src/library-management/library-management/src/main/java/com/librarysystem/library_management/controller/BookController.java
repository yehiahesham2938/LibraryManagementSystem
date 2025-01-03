package com.librarysystem.library_management.controller;

import com.librarysystem.library_management.model.Book;
import com.librarysystem.library_management.model.SystemLog;
import com.librarysystem.library_management.repository.BookRepository;
import com.librarysystem.library_management.repository.SystemLogRepository;
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

    @Autowired
    private SystemLogRepository systemLogRepository;


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
public String ShowSystemLogs(Model model) {
    List<SystemLog> logs = systemLogRepository.findAllByOrderByTimestampDesc();
    model.addAttribute("logs", logs);
    return "Admin/SystemLogs";
}





    @PostMapping("/create-book")
    public String addBook(@ModelAttribute Book book, @RequestParam("coverPhotoFile") MultipartFile coverPhotoFile) throws IOException
    {
        Book existingBook = bookRepository.findByIsbn(book.getIsbn());
        if (existingBook != null) {
            return "error";
        }
        book.setCoverPhoto(coverPhotoFile.getBytes());
        bookRepository.save(book);
        return "Admin/addBook";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
        return "redirect:/admin/view-books";
    }



}
