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
<<<<<<< HEAD
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
=======
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
>>>>>>> 5f941d27a545ad3402306fc0e1da2eeb6a655437
    }

    public void updateCart(OrderDetails orderDetails) { //verify if saving all or just single item
        this.orderDao.save(orderDetails);
    }
}
