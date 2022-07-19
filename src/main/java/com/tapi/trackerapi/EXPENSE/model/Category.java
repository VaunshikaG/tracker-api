package com.tapi.trackerapi.EXPENSE.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "categoryId")
    private Integer categoryId;

    private String title;
    private String description;
    private Double amount;

}
