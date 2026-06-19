package com.example.aleksandr_rozkov_6020_pz1.service;

import com.example.aleksandr_rozkov_6020_pz1.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@ApplicationScope
public class ApplicationStorageService {
    private final List<Manul> manuls = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Suggestion> suggestions = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    private int nextManulId = 9;
    private int nextUserId = 3;
    private int nextSuggestionId = 1;
    private int nextCommentId = 1;

    public ApplicationStorageService() {
        categories.add(new Category(1, "Zoo manuls"));
        categories.add(new Category(2, "Wild manuls"));

        users.add(new User(1, "admin@manuls.com", "admin123", "admin"));
        users.add(new User(2, "user@manuls.com", "user123", "user"));

        manuls.add(new Manul(1, "Batu", "https://cdn.manulization.com/images/cPpAPBjIVjOvwitg_mar-1_mw-600.webp", "A famous Pallas's cat known for his grumpy expression.", "Batu is one of the most recognizable manuls, famous for his expressive face and calm behavior. Like all Pallas's cats, he prefers solitude and rocky habitats.", "ZOO", "Russia", 16, "2024-01-10"));
        manuls.add(new Manul(2, "Wild Manul", "https://upload.wikimedia.org/wikipedia/commons/d/d6/Manoel.jpg", "A wild Pallas's cat from the Mongolian steppes.", "Wild manuls inhabit cold grasslands and rocky steppes of Central Asia. They are excellent hunters but very sensitive to environmental changes.", "WILD", "Mongolia", 23, "2024-02-01"));
        manuls.add(new Manul(3, "Arkas", "https://cdn.manulization.ru/images/8jdAvFBQCGfAInr8_mw-1024.webp", "Bohus and Borsika's son, living at Korkeasaari Zoo.", "Arkas was born at Budapest Zoo and now lives in Korkeasaari Zoo in Helsinki. He is usually calm and spends most of his time in a quiet enclosure.", "ZOO", "Helsinki, Finland", 1, "2026-01-30"));
        manuls.add(new Manul(4, "Mimi", "https://cdn.manulization.ru/images/HzS5sYiODCJ9ldZg_mw-1024.webp", "Norbu and Pema's daughter, living at Korkeasaari Zoo.", "Mimi was born at Chemnitz Zoo and now lives in Korkeasaari Zoo. She is known as the resident lady of the manul pair.", "ZOO", "Helsinki, Finland", 0, "2026-01-30"));
        manuls.add(new Manul(5, "Innokentiy", "https://cdn.manulization.ru/images/rcSqdrqXMVhCTYPV_mw-1024.webp", "A manul from Novosibirsk Zoo line.", "Innokentiy, also known as Kesha, was born at Novosibirsk Zoo and later moved to a center for rare animal reproduction.", "ZOO", "Russia", 1, "2026-01-30"));
        manuls.add(new Manul(6, "Bohus", "https://cdn.manulization.ru/images/Wue2TFb9nRH8Z1j6_mw-1024.webp", "A well-known manul living in Budapest.", "Bohus lives at Budapest Zoo and Botanical Garden. He is one of the better-known zoo manuls in Europe.", "ZOO", "Budapest, Hungary", 0, "2026-01-30"));
        manuls.add(new Manul(7, "Jihl", "https://cdn.manulization.ru/images/rim0xk7rT4DqsxGj_mw-1024.webp", "A young manul living in Paris.", "Jihl was born at Jihlava Zoo and now lives at the Menagerie du Jardin des plantes in Paris.", "ZOO", "Paris, France", 0, "2026-01-30"));
        manuls.add(new Manul(8, "Eru", "https://cdn.manulization.com/images/tK4vn1LL32kH0kJz_mw-1024.webp", "A Pallas's cat living in Higashiyama Zoo.", "Eru was born in Nasu Animal Kingdom and now lives in Higashiyama Zoo and Botanical Gardens. His name is connected with the Mongolian language.", "ZOO", "Japan", 1, "2026-01-31"));
    }

    public List<Manul> getManuls() { return manuls; }
    public List<User> getUsers() { return users; }
    public List<Suggestion> getSuggestions() { return suggestions; }
    public List<Category> getCategories() { return categories; }
    public List<Comment> getComments() { return comments; }

    public Manul getManulById(int id) {
        for (Manul manul : manuls) {
            if (manul.getId() == id) return manul;
        }
        return null;
    }

    public Manul addManul(Manul manul) {
        manul.setId(nextManulId++);
        manul.setLikesCount(0);
        manul.setCreatedAt(LocalDate.now().toString());
        manuls.add(manul);
        return manul;
    }

    public void updateManul(int id, Manul updated) {
        Manul manul = getManulById(id);
        if (manul == null) return;
        manul.setName(updated.getName());
        manul.setPhotoUrl(updated.getPhotoUrl());
        manul.setShortDescription(updated.getShortDescription());
        manul.setLongStory(updated.getLongStory());
        manul.setLocationType(updated.getLocationType());
        manul.setRegion(updated.getRegion());
    }

    public void deleteManul(int id) {
        manuls.removeIf(manul -> manul.getId() == id);
        comments.removeIf(comment -> comment.getManulId() == id);
        suggestions.removeIf(suggestion -> suggestion.getManulId() == id);
    }

    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) return user;
        }
        return null;
    }

    public User registerUser(String email, String password) {
        User user = new User(nextUserId++, email, password, "user");
        users.add(user);
        return user;
    }

    public boolean hasUserLiked(int userId, int manulId) {
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getUserId() == userId && suggestion.getManulId() == manulId && "LIKE".equals(suggestion.getType())) return true;
        }
        return false;
    }

    public boolean addLike(int userId, int manulId) {
        Manul manul = getManulById(manulId);
        if (manul == null || hasUserLiked(userId, manulId)) return false;
        suggestions.add(new Suggestion(nextSuggestionId++, userId, manulId, "LIKE", "", "APPROVED", LocalDateTime.now().toString()));
        manul.setLikesCount(manul.getLikesCount() + 1);
        return true;
    }

    public void addComment(int userId, int manulId, String text) {
        comments.add(new Comment(nextCommentId++, userId, manulId, text, LocalDateTime.now().toString()));
    }

    public List<Comment> getCommentsForManul(int manulId) {
        List<Comment> result = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getManulId() == manulId) result.add(comment);
        }
        return result;
    }

    public void addStorySuggestion(int userId, int manulId, String content) {
        suggestions.add(new Suggestion(nextSuggestionId++, userId, manulId, "STORY", content, "PENDING", LocalDateTime.now().toString()));
    }

    public void approveSuggestion(int id) {
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getId() == id) {
                suggestion.setStatus("APPROVED");
                Manul manul = getManulById(suggestion.getManulId());
                if (manul != null && suggestion.getContent() != null && !suggestion.getContent().isBlank()) {
                    manul.setLongStory(manul.getLongStory() + "\n\nApproved user story: " + suggestion.getContent());
                }
                return;
            }
        }
    }

    public void rejectSuggestion(int id) {
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getId() == id) {
                suggestion.setStatus("REJECTED");
                return;
            }
        }
    }
}
