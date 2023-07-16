package com.paypalpaymentassignment.paypalpayment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypalpaymentassignment.paypalpayment.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer>{
    
}
