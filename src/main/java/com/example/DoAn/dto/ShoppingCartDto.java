package com.example.DoAn.dto;

import com.example.DoAn.entity.User;

import java.util.Set;

public class ShoppingCartDto {
    private User user;
    private double totalPrice;
    private int totalItems;
    private Set<CartItemDto> cartItem;

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(User user, double totalPrice, int totalItems, Set<CartItemDto> cartItem) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
        this.cartItem = cartItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<CartItemDto> getCartItem() {
        return cartItem;
    }

    public void setCartItem(Set<CartItemDto> cartItem) {
        this.cartItem = cartItem;
    }
}
