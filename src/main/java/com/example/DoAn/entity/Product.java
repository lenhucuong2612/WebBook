package com.example.DoAn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="prodcuts")
@JsonIgnoreProperties
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String SKU;

    @Column(unique = true)
    private String ISBN;
    @Column(columnDefinition = "nvarchar(100)")
    private String name;
    @Column(columnDefinition = "nvarchar(100)")
    private String author;
    @Column(columnDefinition = "nvarchar(100)")
    private String object;
    private String framework;
    private int pageNumber;
    @Column(columnDefinition = "nvarchar(100)")
    private String format;
    private int weight;
    @Column(columnDefinition = "nvarchar(100)")
    private String boolSeries;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id",referencedColumnName = "category_id")
    @JsonIgnore
    private Category category;
    private double price;
    private double salePrice;
    private int sold=0;
    private int assess=0;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String description;
    private String imageName;
    @Column(columnDefinition = "nvarchar(100)")
    private String gift;
    private LocalDateTime localDateTime;

    public Product() {
    }

    public Product( String SKU, String ISBN, String name, String author, String object, String framework, int pageNumber, String format, int weight, String boolSeries, Category category, double price, double salePrice, int sold, int assess, String description, String imageName, String gift, LocalDateTime localDateTime) {
        this.SKU = SKU;
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.object = object;
        this.framework = framework;
        this.pageNumber = pageNumber;
        this.format = format;
        this.weight = weight;
        this.boolSeries = boolSeries;
        this.category = category;
        this.price = price;
        this.salePrice = salePrice;
        this.sold = sold;
        this.assess = assess;
        this.description = description;
        this.imageName = imageName;
        this.gift=gift;
        this.localDateTime=localDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBoolSeries() {
        return boolSeries;
    }

    public void setBoolSeries(String boolSeries) {
        this.boolSeries = boolSeries;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getAssess() {
        return assess;
    }

    public void setAssess(int assess) {
        this.assess = assess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", SKU='" + SKU + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", object='" + object + '\'' +
                ", framework='" + framework + '\'' +
                ", pageNumber=" + pageNumber +
                ", format='" + format + '\'' +
                ", weight=" + weight +
                ", boolSeries='" + boolSeries + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", sold=" + sold +
                ", assess=" + assess +
                ", description='" + description + '\'' +
                ", imageName='" + imageName + '\'' +
                ", gift='" + gift + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
