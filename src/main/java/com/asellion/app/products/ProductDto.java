package com.asellion.app.products;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ProductDto {
    private Integer id;
    @NotNull
    private String productName;
    @NotNull
    private Double currentPrice;
    private LocalDateTime lastUpdated;

    public ProductDto(String productName, Double currentPrice) {
        this.productName = productName;
        this.currentPrice = currentPrice;
    }

    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductDto withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductDto withName(String name) {
        this.productName = name;
        return this;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public ProductDto withCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ProductDto withLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

}
