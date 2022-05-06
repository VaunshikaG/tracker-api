package com.tapi.trackerapi.Tracker.domain;

public class Transaction {

    private Integer transactId;
    private Integer categoryId;
    private Integer userId;
    private Double amount;
    private String note;
    private Long transactDate;

    public Transaction(Integer transactId, Integer categoryId, Integer userId, Double amount, String note, Long transactDate) {
        this.transactId = transactId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.amount = amount;
        this.note = note;
        this.transactDate = transactDate;
    }


    public Integer getTransactId() {
        return transactId;
    }

    public void setTransactId(Integer transactId) {
        this.transactId = transactId;
    }

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getTransactDate() {
        return transactDate;
    }

    public void setTransactDate(Long transactDate) {
        this.transactDate = transactDate;
    }

}
