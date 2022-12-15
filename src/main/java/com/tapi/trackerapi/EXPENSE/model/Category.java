package com.tapi.trackerapi.EXPENSE.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Integer categoryId;

    private String title;
    private String description;
    private Integer amount;


    @JsonBackReference
    @ManyToOne
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private User user;

}
