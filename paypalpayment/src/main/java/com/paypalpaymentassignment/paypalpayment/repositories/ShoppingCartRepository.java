package com.paypalpaymentassignment.paypalpayment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypalpaymentassignment.paypalpayment.domain.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer>{
    
}
