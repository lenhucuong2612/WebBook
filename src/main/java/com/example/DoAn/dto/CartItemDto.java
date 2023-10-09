package com.example.DoAn.dto;

public class CartItemDto {
    private ShoppingCartDto cart;
    private ProductDTO product;
    private int quantity;
    private double unitPrice;

    public CartItemDto() {
    }

    public CartItemDto(ShoppingCartDto cart, ProductDTO product, int quantity, double unitPrice) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public ShoppingCartDto getCart() {
        return cart;
    }

    public void setCart(ShoppingCartDto cart) {
        this.cart = cart;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
