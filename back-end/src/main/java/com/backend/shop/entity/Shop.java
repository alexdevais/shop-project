package com.backend.shop.entity;

import jakarta.persistence.*;

import java.util.List;


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

    public Shop(String name, String city, String address, Integer postalCode, User owner) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.owner = owner;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }
}
