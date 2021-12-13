package com.revature.revatrade.repository;

import com.revature.revatrade.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("OrderDao")
@Transactional
public interface OrderDao extends JpaRepository<Order, Integer> {
}
