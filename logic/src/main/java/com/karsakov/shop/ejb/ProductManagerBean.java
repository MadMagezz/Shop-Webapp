package com.karsakov.shop.ejb;

import com.karsakov.shop.domain.Product;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class ProductManagerBean {

    @PersistenceContext(unitName = "MySqlPU")
    private EntityManager entityManager;

    public Product createProduct(String name, int quantity) {
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        entityManager.persist(product);

        return product;
    }

    public List<Product> getProduct() {
        TypedQuery<Product> query = entityManager.createQuery("select c from Product c", Product.class);
        return query.getResultList();
    }

}
