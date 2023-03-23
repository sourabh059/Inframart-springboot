package com.InfraMart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InfraMart.beans.Checkout;

@Repository
public interface CheckoutDao extends JpaRepository<Checkout, Long>{

}
