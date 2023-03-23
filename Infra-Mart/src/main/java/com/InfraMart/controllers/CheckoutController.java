package com.InfraMart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InfraMart.beans.Checkout;
import com.InfraMart.service.CheckoutService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/checkout")
public class CheckoutController
{
	@Autowired
	private CheckoutService chkoutService;

	//order details
	@PostMapping("/orderdetails")
	public ResponseEntity<Checkout> addOrderDetails(@RequestBody Checkout chkout)
	{
		Checkout ordDetails=chkoutService.placeOrder(chkout);
		if(ordDetails!=null)
		{
			return new ResponseEntity("Your order has been placed successfully!!",HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}



}
