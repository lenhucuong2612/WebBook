package com.example.DoAn.dto;

import com.example.DoAn.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties
public class SearchDto {
    private List<Product> product;
    private String name;
    private int quantity;

    public SearchDto(List<Product> product, String name, int quantity) {
        this.product = product;
        this.name = name;
        this.quantity = quantity;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SearchDto{" +
                "product=" + product +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
