package com.beyonder.model;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @NotNull
    @Size(min = 2, max = 255) // Increased size to 255 for product names
    @Column()
    private String name;

    @NotNull
    @Size(min = 2, max = 100) // Kept size at 100 for product codes
    @Column(length = 100)
    private String code;

    @NotNull
    @DecimalMin("0.0")
    private Double priceItem;

    @NotNull
    @DecimalMin("0.0")
    private Double priceItemfo;

    @Column(name = "abik_fo")
    private String abi;

    @NotNull
    @Min(0)
    private Integer stock;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 100) // Assuming the user name or id who created the product, size can be adjusted based on actual data
    private String createdBy;

    @Column(name = "updated_by", length = 100) // Assuming the user name or id who updated the product, size can be adjusted based on actual data
    private String updatedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(Double price) {
        this.priceItem = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getAbi() {
        return abi;
    }

    public void setAbi(String abi) {
        this.abi = abi;
    }

    public Double getPriceItemfo() {
        return priceItemfo;
    }

    public void setPriceItemfo(Double priceItemfo) {
        this.priceItemfo = priceItemfo;
    }
}
