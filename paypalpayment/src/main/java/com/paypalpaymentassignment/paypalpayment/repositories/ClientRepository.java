package com.paypalpaymentassignment.paypalpayment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypalpaymentassignment.paypalpayment.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{
    
}
