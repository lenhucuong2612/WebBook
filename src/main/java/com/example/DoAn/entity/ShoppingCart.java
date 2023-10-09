package com.example.DoAn.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shopping_cart_id")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;
    private double totalPrice;
    private int totalItems;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;
    public User getUser() {
        return user;
    }

    public ShoppingCart() {
        this.totalPrice = 0;
        this.totalItems = 0;
        this.cartItems = new HashSet<>();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
