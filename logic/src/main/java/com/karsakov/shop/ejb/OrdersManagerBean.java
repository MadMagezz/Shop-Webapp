package com.karsakov.shop.ejb;


import com.karsakov.shop.domain.Order;
import com.karsakov.shop.domain.Product;
import com.karsakov.shop.domain.ProductInOrder;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Stateless
@LocalBean
public class OrdersManagerBean {

    @PersistenceContext(unitName = "MySqlPU")
    private EntityManager entityManager;

    public Order createOrder() {
        Order order = new Order();
        entityManager.persist(order);
        return order;
    }

    public boolean addToOrder(long productId, long orderId, int quantity){
        Product product = entityManager.find(Product.class, productId);
        if (product == null) {
            return false;
        }
        Order order = entityManager.find(Order.class, orderId);
        if(order == null) {
            return false;
        }
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setProduct(product);
        productInOrder.setOrder(order);
        productInOrder.setQuantity(quantity);

        entityManager.persist(productInOrder);

        return true;
    }

    public List<Product> getProductInOrder(long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        if(order == null) {
            return Collections.emptyList();
        }
        List<ProductInOrder> productInOrders = order.getProductsInOrders();
        List<Product> result = new ArrayList<>();

        for(ProductInOrder productInOrder : productInOrders) {
            result.add(productInOrder.getProduct());
        }
        return result;
    }
}
