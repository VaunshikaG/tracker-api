package com.tapi.trackerapi.NEW.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @Column(name = "categoryId")
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name ="userId")
    private User user;

    @Column(name = "title", nullable = false, length = 10)
    private String title;

    @Column(name = "description", nullable = false, length = 10)
    private String description;

    @Column(name = "totalexpense", nullable = false, length = 10)
    private double totalexpense;

    public Category() {
        super();
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Long getcategoryId() { return categoryId; }

    public void setcategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getTotalexpense() { return totalexpense; }

    public void setTotalexpense(double totalexpense) { this.totalexpense = totalexpense; }

    public Category(Long categoryId, String title, String description, double totalexpense) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.totalexpense = totalexpense;
    }

}
