package com.iplapp.domain;

public class Player {
    
    public String _id;
    public String name;
    public String price;
    public String role;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "Player [name=" + name + ", price=" + price + ", role=" + role + "]";
    }
    
    
}
