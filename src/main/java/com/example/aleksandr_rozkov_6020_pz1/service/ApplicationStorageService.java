package com.example.aleksandr_rozkov_6020_pz1.service;

import com.example.aleksandr_rozkov_6020_pz1.model.*;

import java.util.ArrayList;
import java.util.List;

public class ApplicationStorageService {

    private List<Manul> manuls;
    private List<User> users;
    private List<Suggestion> suggestions;
    private List<Category> categories;
    private List<Comment> comments;

    public ApplicationStorageService() {
        manuls = new ArrayList<>();
        users = new ArrayList<>();
        suggestions = new ArrayList<>();
        categories = new ArrayList<>();
        comments = new ArrayList<>();

        manuls.add(
                new Manul(
                        1,
                        "Batu",
                        "",
                        "Famous manul",
                        "Long story",
                        "ZOO",
                        "Russia",
                        10,
                        5,
                        "2026"
                )
        );

        manuls.add(
                new Manul(
                        2,
                        "Mimi",
                        "",
                        "Cute manul",
                        "Long story",
                        "ZOO",
                        "Finland",
                        15,
                        8,
                        "2026"
                )
        );
    }

    public List<Manul> getManuls() {
        return manuls;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Comment> getComments() {
        return comments;
    }
}