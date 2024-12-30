package com.librarysystem.library_management.controller;

import com.librarysystem.library_management.model.SystemLog;
import com.librarysystem.library_management.model.User;
import com.librarysystem.library_management.repository.SystemLogRepository;
import com.librarysystem.library_management.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private SystemLogRepository systemLogRepository;


    @GetMapping("/")
    public String showLoginPage()
    {
        return "login";  }

    @GetMapping("/login")
    public String ShowAfterRegister()
    {
        return "login";  }


    @PostMapping("/logout")
    public String logout() {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request) {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            systemLogRepository.save(new SystemLog("Failed login attempt with username: " + username));
            model.addAttribute("error", "Invalid credentials.");
            return "login";
        }

        if (user.getPassword().equals(password)) {
            request.getSession().setAttribute("userId", user.getUsername());
            systemLogRepository.save(new SystemLog("User " + username + " logged in successfully"));

            if (user.getRole().equals("Admin")) {
                return "Admin/admin";
            } else {
                return "User/home";
            }
        }
        else {
            systemLogRepository.save(new SystemLog("Failed login attempt for user: " + username));
            model.addAttribute("error", "Invalid credentials.");
            return "login";
        }
    }


}
