package com.tapi.trackerapi.EXPENSE.model;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "categoryId", nullable = false)
    private Integer categoryId;

    @NotEmpty
    @Size(min = 3, max = 15, message = "Title length must be between 3 to 10 characters")
    @Column(nullable = false)
    private String title;

    @NotEmpty
    @Size(min = 5, max = 50, message = "Title length must be between 3 to 10 characters")
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double amount;

}
