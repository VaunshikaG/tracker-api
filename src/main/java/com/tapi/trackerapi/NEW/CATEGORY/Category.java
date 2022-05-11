package com.tapi.trackerapi.NEW.CATEGORY;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tapi.trackerapi.NEW.USER.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "categoryId")
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "categoryId")
    private Integer categoryId;

    private String title;
    private String description;
    private Double totalexpense;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public User user;

    public Category() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
//        user.getUserId();
        this.user = user;
    }

    public Integer getcategoryId() {
        return categoryId;
    }

    public void setcategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Double getTotalexpense() {
        return totalexpense;
    }

    public void setTotalexpense(double totalexpense) {
        this.totalexpense = totalexpense;
    }

    public Category(Integer categoryId, String title, String description, Double totalexpense) {
        super();
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.totalexpense = totalexpense;
    }

}
