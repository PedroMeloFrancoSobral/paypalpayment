package com.paypalpaymentassignment.paypalpayment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypalpaymentassignment.paypalpayment.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{
    
}
