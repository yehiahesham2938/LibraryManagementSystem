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
    @GetMapping("/")
    public String showLoginPage() {
        return "login";  }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            if (user.getRole().equals("Admin")) {
                return "admin";
            } else {
                return "home";
            }
        } else {
            model.addAttribute("error", "Invalid credentials.");
            return "login";
        }
    }
}
