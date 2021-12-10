package com.revature.revatrade.repository;

import com.revature.revatrade.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.revatrade.model.Order;
import org.springframework.transaction.annotation.Transactional;


@Repository("CartDao")
@Transactional
public interface ShoppingCartDao extends JpaRepository<OrderDetails, Integer>{
    }


