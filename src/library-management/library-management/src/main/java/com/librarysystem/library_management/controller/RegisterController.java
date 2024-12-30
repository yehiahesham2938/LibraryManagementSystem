package com.librarysystem.library_management.controller;

import com.librarysystem.library_management.model.SystemLog;
import com.librarysystem.library_management.model.User;
import com.librarysystem.library_management.repository.SystemLogRepository;
import com.librarysystem.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SystemLogRepository systemLogRepository;

    @GetMapping("/register")
    public String RegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            systemLogRepository.save(new SystemLog("Failed registration attempt - Username already exists: " + user.getUsername()));
            redirectAttributes.addFlashAttribute("error", "Username already exists.");
            return "redirect:/register";
        }
        user.setRole("User");
        userRepository.save(user);
        systemLogRepository.save(new SystemLog("New user registered: " + user.getUsername()));
        return "login";
    }

}
