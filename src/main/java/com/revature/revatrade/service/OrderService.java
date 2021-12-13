package com.revature.revatrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.revature.revatrade.model.Order;
import com.revature.revatrade.repository.OrderDao;

@Service("orderService")
public class OrderService {
	OrderDao orderDao;
	
	@Autowired
	OrderService(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}
	public void save(Order order)
	{
		orderDao.save(order);
	}
}
