package com.paypalpaymentassignment.paypalpayment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypalpaymentassignment.paypalpayment.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    
}
