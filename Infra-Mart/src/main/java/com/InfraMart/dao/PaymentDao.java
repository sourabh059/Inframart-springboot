package com.InfraMart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.InfraMart.beans.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long>{

}
