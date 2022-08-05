package com.tapi.trackerapi.EXPENSE.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "category")

public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Integer categoryId;

    private String title;
    private String description;
    private Double amount;


    @JsonBackReference
    @ManyToOne
    private User user;

}
