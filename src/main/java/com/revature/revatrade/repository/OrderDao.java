package com.revature.revatrade.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.revatrade.model.Order;


@Repository("orderDao")
public interface OrderDao extends JpaRepository <Order, Integer> {

}
