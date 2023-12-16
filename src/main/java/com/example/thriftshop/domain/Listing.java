package com.example.thriftshop.domain;

import java.math.BigDecimal;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Listing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private BigDecimal price;
    private java.util.Date date;

    
    
    private String pictureURL;

    private String condition;
    private String details;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Listing () {}

    public Listing(String name, BigDecimal price, java.util.Date date, String pictureURL, String condition, String details,
            Category category) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.pictureURL = pictureURL;
        this.condition = condition;
        this.details = details;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPicture(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    
}
