package com.revature.revatrade.repository;

import com.revature.revatrade.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("CartDao")
@Transactional
public interface ShoppingCartDao extends JpaRepository <OrderDetails, Integer> {

   public List<OrderDetails> findByUserIdContaining(String userId);

   public <S extends OrderDetails> S save(S orders);

}


