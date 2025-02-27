package com.backend.shop.entity;


import com.backend.shop.enums.ProductCategory;
import com.backend.shop.enums.ProductSize;
import jakarta.persistence.*;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false, length = 100)
    private String colour;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductSize size;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    public Product() {
    }

    public Product(String description, String colour, ProductCategory category, ProductSize size) {
        this.description = description;
        this.colour = colour;
        this.category = category;
        this.size = size;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public ProductSize getSize() {
        return size;
    }

    public void setSize(ProductSize size) {
        this.size = size;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
