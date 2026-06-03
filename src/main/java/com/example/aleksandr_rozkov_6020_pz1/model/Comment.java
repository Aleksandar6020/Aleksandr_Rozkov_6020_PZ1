package com.example.aleksandr_rozkov_6020_pz1.model;

public class Comment {
    private int id;
    private int userId;
    private int manulId;
    private String text;
    private String createdAt;

    public Comment() {
    }

    public Comment(int id, int userId, int manulId, String text, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.manulId = manulId;
        this.text = text;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getManulId() { return manulId; }
    public void setManulId(int manulId) { this.manulId = manulId; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}