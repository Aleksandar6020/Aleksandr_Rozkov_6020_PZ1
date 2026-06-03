package com.example.aleksandr_rozkov_6020_pz1.model;

public class Suggestion {
    private int id;
    private int userId;
    private int manulId;
    private String type;
    private String content;
    private String status;
    private String createdAt;

    public Suggestion() {
    }

    public Suggestion(int id, int userId, int manulId, String type, String content, String status, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.manulId = manulId;
        this.type = type;
        this.content = content;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getManulId() { return manulId; }
    public void setManulId(int manulId) { this.manulId = manulId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}