package com.revature.revatrade.service;

import com.revature.revatrade.model.OrderDetails;
import com.revature.revatrade.repository.ShoppingCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class ShoppingCartService {
    ShoppingCartDao cartDao;

    @Autowired
    public ShoppingCartService(ShoppingCartDao orderDao) {
        this.cartDao = orderDao;
    }

    public void addItem(OrderDetails orderDetails) {
        this.cartDao.save(orderDetails);
    } //verified

    public List<OrderDetails> findAll() {
        return this.cartDao.findAll();
    } //verified

    public void deleteItem(OrderDetails orderDetails) {
        this.cartDao.deleteById(orderDetails.getOrderDetailsId());
    }

    public void updateCart(OrderDetails orderDetails) { //verify if saving all or just single item
        this.cartDao.save(orderDetails);
    }
}
