import com.karsakov.shop.domain.Order;
import com.karsakov.shop.domain.Product;
import com.karsakov.shop.ejb.OrdersManagerBean;
import com.karsakov.shop.ejb.ProductManagerBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class OrderBean implements Serializable {

    private Order order;
    private String name;
    private int quantity;

    @EJB
    private OrdersManagerBean ordersManagerBean;

    @EJB
    private ProductManagerBean productManagerBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void createOrder() {
        if(order == null) {
           order = ordersManagerBean.createOrder();
        }
    }

    public void createProduct() {
        productManagerBean.createProduct(name, quantity);
    }

    public List<Product> getProduct() {
        return productManagerBean.getProduct();
    }

    public void addProduct(Product product){
        if(order==null){
            return;
        }
        ordersManagerBean.addToOrder(product.getId(), order.getId(), 1);
    }

    public List<Product> getProductInOrder() {
        if(order==null) {
            return Collections.emptyList();
        }

        return ordersManagerBean.getProductInOrder(order.getId());
    }
}
