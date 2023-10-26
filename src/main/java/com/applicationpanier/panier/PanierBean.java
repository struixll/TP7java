package com.applicationpanier.panier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

@Stateful
public class PanierBean implements CartBeanLocal {
    private List<Product> cart = new ArrayList<>();
    
    @Override
    public List<Product> getCartItems() {
        return cart;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void addProductToCart(Product product) {
        cart.add(product);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void checkOut() {
        EntityManager entityManager = null;
         for (Product product : cart) {
             entityManager.persist(product); 
         }
    }

    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void removeProduct(String productId) {
        Iterator<Product> iterator = cart.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId().equals(productId)) {
                iterator.remove();
                break;
            }
        }
    }

}
