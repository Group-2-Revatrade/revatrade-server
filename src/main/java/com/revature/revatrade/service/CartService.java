package com.revature.revatrade.service;

import com.revature.revatrade.model.OrderDetails;
import com.revature.revatrade.model.Product;
import com.revature.revatrade.repository.OrderDao;
import com.revature.revatrade.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartService {
    OrderDao orderDao;

    @Autowired
    public CartService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void addItem(OrderDetails orderDetails) {
        this.orderDao.save(orderDetails);
    } //verified

    public List<OrderDetails> findAll() {
        return this.orderDao.findAll();
    } //verified

    public void deleteItem(OrderDetails orderDetails) {
        this.orderDao.deleteById(orderDetails.getOrderDetailsId());
    }

    public void updateCart(OrderDetails orderDetails) { //verify if saving all or just single item
        this.orderDao.save(orderDetails);
    }
}
