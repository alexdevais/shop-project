package com.backend.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shopId;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 5)
    private Integer postalCode;

    @Column(nullable = false, length = 50)
    private String country;

    @OneToOne(mappedBy = "shop")
    private Inventory inventory;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(nullable = true)
    @ManyToMany
    @JoinTable(
            name = "shop_employee",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<User> employees;

    public Shop() {
    }

    public Shop(String name, String city, String address, Integer postalCode, String country, User owner) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.owner = owner;
    }
}
