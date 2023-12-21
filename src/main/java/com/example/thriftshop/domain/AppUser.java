package com.example.thriftshop.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private Long userId;

   @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    private String email;
    private String phone;
    private String address;
    private String postcode;
    private String city;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Listing> listings;
    

    public AppUser() {
    }


    public AppUser(String username, String password, String role, String email, String phone, String address,
            String postcode, String city) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
    }


    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getPostcode() {
        return postcode;
    }


    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "AppUser [UserId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role
                + ", email=" + email + ", phone=" + phone + ", address=" + address + ", postcode=" + postcode
                + ", city=" + city + "]";
    }

   

    
}