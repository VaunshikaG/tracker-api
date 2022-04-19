package com.tapi.trackerapi.domain;

public class Category {
    private Integer categoryId;
    private Integer userId;
    private String title;
    private String description;
    private double totalexpense;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalexpense() {
        return totalexpense;
    }

    public void setTotalexpense(double totalexpense) {
        this.totalexpense = totalexpense;
    }

    public Category(Integer categoryId, Integer userId, String title, String description, double totalexpense) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.totalexpense = totalexpense;
    }

}
