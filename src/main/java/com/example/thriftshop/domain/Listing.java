package com.example.thriftshop.domain;

import java.math.BigDecimal;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Listing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private BigDecimal price;
    private java.util.Date date;

    
    @Lob
    private byte[] pictureData;

    private String condition;
    private String details;


 

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser appUser;

    public Listing () {}

    public Listing(String name, BigDecimal price, java.util.Date date, byte[] pictureData, String condition, String details,
            Category category, AppUser appUser) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.pictureData = pictureData;
        this.condition = condition;
        this.details = details;
      
        this.category = category;
        this.appUser = appUser;
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

    public byte[] getPictureData() {
        return pictureData;
    }

    public void setPictureData(byte[] pictureData) {
        this.pictureData = pictureData;
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
    
    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "Listing [id=" + id + ", name=" + name + ", price=" + price + ", date=" + date + ", pictureURL="
                + pictureData + ", condition=" + condition + ", details=" + details + ", category=" + category + "]";
    }
    
    
}
