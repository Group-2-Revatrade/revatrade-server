package com.revature.revatrade.service;

import com.revature.revatrade.model.OrderDetails;
import com.revature.revatrade.repository.ShoppingCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.DirectoryStream;
import java.util.Collections;
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

    public void deleteItem(Integer order_id) {
        this.cartDao.deleteById(order_id);
    }

    public void updateCart(OrderDetails orderDetails) { //verify if saving all or just single item
        this.cartDao.save(orderDetails);
    }

    public List<OrderDetails> findAllById(Integer userId) {

        return this.cartDao.findOrdersByID(userId).stream().toList();

    }
}
