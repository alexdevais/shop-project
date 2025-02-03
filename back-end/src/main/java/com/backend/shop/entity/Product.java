package com.backend.shop.entity;


import com.backend.shop.enums.ProductCategory;
import com.backend.shop.enums.ProductSize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
