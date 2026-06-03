package com.example.aleksandr_rozkov_6020_pz1.model;

public class Manul {
    private int id;
    private String name;
    private String photoUrl;
    private String shortDescription;
    private String longStory;
    private String locationType;
    private String region;
    private int likesCount;
    private int favoritesCount;
    private String createdAt;

    public Manul() {
    }

    public Manul(int id, String name, String photoUrl, String shortDescription, String longStory, String locationType, String region, int likesCount, int favoritesCount, String createdAt) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.shortDescription = shortDescription;
        this.longStory = longStory;
        this.locationType = locationType;
        this.region = region;
        this.likesCount = likesCount;
        this.favoritesCount = favoritesCount;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public String getLongStory() { return longStory; }
    public void setLongStory(String longStory) { this.longStory = longStory; }

    public String getLocationType() { return locationType; }
    public void setLocationType(String locationType) { this.locationType = locationType; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public int getLikesCount() { return likesCount; }
    public void setLikesCount(int likesCount) { this.likesCount = likesCount; }

    public int getFavoritesCount() { return favoritesCount; }
    public void setFavoritesCount(int favoritesCount) { this.favoritesCount = favoritesCount; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}