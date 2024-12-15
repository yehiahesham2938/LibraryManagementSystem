package com.librarysystem.library_management.controller;

import com.librarysystem.library_management.model.User;
import com.librarysystem.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Handles GET request to show the login page
    @GetMapping("/")
    public String showLoginPage() {
        return "login";  // This returns login.html as the login page
    }

    // Handles POST request for form submission
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Check credentials from the database
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            if (user.getRole().equals("Admin")) {
                return "admin";  // Redirect to admin page if user is admin
            } else {
                return "home";   // Redirect to home page for other users
            }
        } else {
            model.addAttribute("error", "Invalid credentials.");
            return "login";  // Return to login page if authentication fails
        }
    }
}
