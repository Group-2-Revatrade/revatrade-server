package com.revature.revatrade.service;

import com.revature.revatrade.model.*;
import com.revature.revatrade.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service("orderDetailsService")
public class OrderDetailsService {

    OrderDetailsDao orderDetailsDao;
    UserDao userDao;
    UserProfileDao userProfileDao;
    OrderDao orderDao;
    ProductDao productDao;

    @Autowired
    OrderDetailsService(OrderDetailsDao orderDetailsDao,
                        OrderDao orderDao,
                        ProductDao productDao)
    {
        this.orderDetailsDao = orderDetailsDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    public OrderDetails save(OrderDetails orderDetails, Integer orderId, Integer productId)
    {
        // Retrieving order and product
        Order order = this.orderDao.findById(orderId).orElse(null);
        Product product = this.productDao.findById(productId).orElse(null);
        if(order != null && product != null) {
            // Set foreign keys
            orderDetails.setOrder(order);
            orderDetails.setProduct(product);
            // Saving an order detail...
            return orderDetailsDao.save(orderDetails);
        }
        return null;
    }
}
