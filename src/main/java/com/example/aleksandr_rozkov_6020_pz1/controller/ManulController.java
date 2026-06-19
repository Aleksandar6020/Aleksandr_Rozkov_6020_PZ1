package com.example.aleksandr_rozkov_6020_pz1.controller;

import com.example.aleksandr_rozkov_6020_pz1.model.Manul;
import com.example.aleksandr_rozkov_6020_pz1.model.User;
import com.example.aleksandr_rozkov_6020_pz1.service.ApplicationStorageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ManulController {
    private final ApplicationStorageService storageService;

    public ManulController(ApplicationStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/manuls")
    public String showManuls(@RequestParam(defaultValue = "createdAt") String sortBy,
                             @RequestParam(defaultValue = "1") int page,
                             Model model,
                             HttpSession session) {
        int limit = 6;
        List<Manul> sorted = new ArrayList<>(storageService.getManuls());
        if ("name".equals(sortBy)) sorted.sort(Comparator.comparing(Manul::getName));
        else if ("likesCount".equals(sortBy)) sorted.sort(Comparator.comparing(Manul::getLikesCount).reversed());
        else sorted.sort(Comparator.comparing(Manul::getCreatedAt).reversed());

        int total = sorted.size();
        int totalPages = Math.max(1, (int) Math.ceil((double) total / limit));
        page = Math.max(1, Math.min(page, totalPages));
        int from = (page - 1) * limit;
        int to = Math.min(from + limit, total);

        model.addAttribute("manuls", sorted.subList(from, to));
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("total", total);
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "manuls";
    }

    @GetMapping("/manuls/{id}")
    public String showManulDetails(@PathVariable int id, Model model, HttpSession session) {
        Manul manul = storageService.getManulById(id);
        if (manul == null) return "notfound";
        User currentUser = (User) session.getAttribute("currentUser");
        boolean likedByCurrentUser = currentUser != null && storageService.hasUserLiked(currentUser.getId(), id);
        model.addAttribute("manul", manul);
        model.addAttribute("comments", storageService.getCommentsForManul(id));
        model.addAttribute("likedByCurrentUser", likedByCurrentUser);
        model.addAttribute("currentUser", currentUser);
        return "manuldetails";
    }

    @PostMapping("/manuls/{id}/like")
    public String likeManul(@PathVariable int id, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";
        storageService.addLike(currentUser.getId(), id);
        return "redirect:/manuls/" + id;
    }

    @PostMapping("/manuls/{id}/comments")
    public String addComment(@PathVariable int id, @RequestParam String text, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";
        if (text != null && !text.isBlank()) storageService.addComment(currentUser.getId(), id, text);
        return "redirect:/manuls/" + id;
    }

    @PostMapping("/manuls/{id}/suggestions")
    public String addSuggestion(@PathVariable int id, @RequestParam String content, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/login";
        if (content != null && !content.isBlank()) storageService.addStorySuggestion(currentUser.getId(), id, content);
        return "redirect:/manuls/" + id;
    }
}
