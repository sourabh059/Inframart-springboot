package com.InfraMart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InfraMart.beans.Checkout;
import com.InfraMart.dao.CheckoutDao;

@Service
public class CheckoutServiceImpl implements CheckoutService
{

	@Autowired
	private CheckoutDao chkdao;
	
	@Override
	public Checkout placeOrder(Checkout chkout) 
	{
		
		return chkdao.save(chkout);
	}
	
}
