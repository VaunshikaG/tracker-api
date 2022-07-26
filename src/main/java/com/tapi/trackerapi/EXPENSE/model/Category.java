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
    @Column(name = "categoryId")
    private Integer categoryId;

    @NotEmpty
    @Size(min = 3, max = 15, message = "Title length must be between 3 to 10 characters")
    @Column(nullable = false, length = 50)
    private String title;

    @NotEmpty
    @Size(min = 5, max = 50, message = "Title length must be between 3 to 10 characters")
    @Column(nullable = false, length = 100)
    private String description;

    @NotEmpty
    @Size(min = 0, message = "Amount must be greater than 0")
    private Double amount;

}
