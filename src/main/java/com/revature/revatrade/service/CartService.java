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
        orderDao = orderDao;
    }

    public void addItem(OrderDetails orderDetails) {
        orderDao.save(orderDetails);
    }

    public List<OrderDetails> findAll() {
        return orderDao.findAll();
    }

    public void deleteItem(OrderDetails orderDetails) {
        orderDao.deleteById(orderDetails.getOrderDetailsId());
    }

    public void updateCart(OrderDetails orderDetails) { //verify if saving all or just single item
        this.orderDao.save(orderDetails);
    }
}
