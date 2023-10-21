package com.applicationpanier.panier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

@Stateful
public class CartBean implements CartBeanLocal {
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
        for (Product product : cart) {
            if (product.getId().equals(productId)) {
                 cart.remove(product);
                 break; 
             }
         }
    }
}
