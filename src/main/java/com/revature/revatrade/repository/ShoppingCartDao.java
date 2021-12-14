package com.revature.revatrade.repository;

import com.revature.revatrade.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("CartDao")
@Transactional
public interface ShoppingCartDao extends JpaRepository<OrderDetails, Integer>{

    @Query("SELECT '*' FROM OrderDetails  where orderDetailsId = '?' ")
    List<OrderDetails> findOrdersByID(Integer userId);

}


