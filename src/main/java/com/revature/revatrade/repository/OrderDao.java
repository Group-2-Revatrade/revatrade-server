package com.revature.revatrade.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.revatrade.model.Order;


@Repository("orderDao")
@Transactional
public interface OrderDao extends JpaRepository <Order, Integer> {

}
