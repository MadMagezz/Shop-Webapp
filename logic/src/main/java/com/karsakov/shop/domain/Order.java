package com.karsakov.shop.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="zakaz")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "order")
    private List<ProductInOrder> productsInOrders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductInOrder> getProductsInOrders() {
        return productsInOrders;
    }

    public void setProductsInOrders(List<ProductInOrder> productsInOrders) {
        this.productsInOrders = productsInOrders;
    }
}
