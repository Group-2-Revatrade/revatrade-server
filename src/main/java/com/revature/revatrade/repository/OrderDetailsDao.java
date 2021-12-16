package com.revature.revatrade.repository;

import com.revature.revatrade.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("orderDetailsDao")
@Transactional
public interface OrderDetailsDao extends JpaRepository<OrderDetails, Integer> {

}
