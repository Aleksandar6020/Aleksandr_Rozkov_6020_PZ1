package com.example.aleksandr_rozkov_6020_pz1.controller;

import com.example.aleksandr_rozkov_6020_pz1.model.Manul;
import com.example.aleksandr_rozkov_6020_pz1.model.User;
import com.example.aleksandr_rozkov_6020_pz1.service.ApplicationStorageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    private final ApplicationStorageService storageService;

    public AdminController(ApplicationStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/admin")
    public String admin(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"admin".equals(currentUser.getRole())) return "redirect:/login";
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("manuls", storageService.getManuls());
        model.addAttribute("suggestions", storageService.getSuggestions());
        model.addAttribute("newManul", new Manul());
        return "admin";
    }

    @PostMapping("/admin/manuls")
    public String createManul(@ModelAttribute Manul manul, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        storageService.addManul(manul);
        return "redirect:/admin";
    }

    @GetMapping("/admin/manuls/{id}/edit")
    public String editManul(@PathVariable int id, Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        Manul manul = storageService.getManulById(id);
        if (manul == null) return "notfound";
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        model.addAttribute("manul", manul);
        return "editmanul";
    }

    @PostMapping("/admin/manuls/{id}/edit")
    public String updateManul(@PathVariable int id, @ModelAttribute Manul manul, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        storageService.updateManul(id, manul);
        return "redirect:/admin";
    }

    @PostMapping("/admin/manuls/{id}/delete")
    public String deleteManul(@PathVariable int id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        storageService.deleteManul(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/suggestions/{id}/approve")
    public String approveSuggestion(@PathVariable int id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        storageService.approveSuggestion(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/suggestions/{id}/reject")
    public String rejectSuggestion(@PathVariable int id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        storageService.rejectSuggestion(id);
        return "redirect:/admin";
    }

    private boolean isAdmin(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        return currentUser != null && "admin".equals(currentUser.getRole());
    }
}
