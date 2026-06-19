package com.example.aleksandr_rozkov_6020_pz1.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "index";
    }
}
