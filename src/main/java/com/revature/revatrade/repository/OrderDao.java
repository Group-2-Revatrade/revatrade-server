package com.revature.revatrade.repository;

import com.revature.revatrade.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.revatrade.model.Order;


@Repository("OrderDao")
@Transactional
public interface OrderDao extends JpaRepository<OrderDetails, Integer>{
    }

}
