package com.example.DoAn.dto;
import java.time.LocalDateTime;
public class ProductDTO {
    private Long id;

    private String SKU;
    private String ISBN;
    private String name;
    private String author;
    private String object;
    private String framework;
    private int pageNumber;
    private String format;
    private int weight;
    private String boolSeries;
    private Long categoryId;
    private double price;
    private double salePrice;
    private int sold=0;
    private int assess=0;
    private String description;
    private String gift;
    private String imageName;
    private LocalDateTime localDateTime;

    public ProductDTO() {
    }

    public ProductDTO(String SKU, String ISBN, String name, String author, String object, String framework, int pageNumber, String format, int weight, String boolSeries, Long categoryId, double price, double salePrice, int sold, int assess, String description, String imageName,String gift) {
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
        this.categoryId = categoryId;
        this.price = price;
        this.salePrice = salePrice;
        this.sold = sold;
        this.assess = assess;
        this.description = description;
        this.gift=gift;
        this.imageName = imageName;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
}
