package com.tapi.trackerapi.EXPENSE.model;

import lombok.Data;


@Data
public class CategoryDto {
    private Integer categoryId;
    private String title;
    private String description;
    private Integer amount;
    private UserDto user;

}
