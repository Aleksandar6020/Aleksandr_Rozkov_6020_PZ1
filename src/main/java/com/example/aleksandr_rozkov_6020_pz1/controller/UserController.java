package com.example.aleksandr_rozkov_6020_pz1.controller;

import com.example.aleksandr_rozkov_6020_pz1.model.User;
import com.example.aleksandr_rozkov_6020_pz1.service.ApplicationStorageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final ApplicationStorageService storageService;

    public UserController(ApplicationStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        User user = storageService.findUserByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
        session.setAttribute("currentUser", user);
        if ("admin".equals(user.getRole())) return "redirect:/admin";
        return "redirect:/manuls";
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession session) {
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@RequestParam String email, @RequestParam String password, Model model) {
        if (!email.contains("@") || !email.substring(email.indexOf('@')).contains(".")) {
            model.addAttribute("error", "Email must contain @ and a dot after @.");
            return "register";
        }
        if (storageService.findUserByEmail(email) != null) {
            model.addAttribute("error", "User with this email already exists.");
            return "register";
        }
        storageService.registerUser(email, password);
        return "redirect:/login?registered=true";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
