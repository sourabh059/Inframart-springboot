package com.InfraMart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InfraMart.beans.Product;
import com.InfraMart.beans.User;
import com.InfraMart.service.ProductService;
import com.InfraMart.service.UserService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/home")
public class HomeController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//user registration
	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody User u)
	{
		User u1=userService.addNewUser(u);
		if(u1!=null)
		{
			return new ResponseEntity("Registration successful!!",HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	//user,admin,manager login
	@PostMapping("/login")
	public ResponseEntity<User> checkCredentials(@RequestBody User u)
	{
		User u1=userService.isValid(u);
//		System.out.println(u1.getEmail()+"   "+u1.getPassword());
//		System.out.println(u.getPassword());
		if(u1!=null)
		{	
			if(passwordEncoder.matches(u.getPassword(),u1.getPassword()))
			{
				return ResponseEntity.ok(u1);
			}
			else
				return new ResponseEntity("Wrong Password ",HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity("Wrong Email ",HttpStatus.NOT_FOUND);
	}
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProducts()
	{
		List<Product> plist=productService.displayAllProducts();
		return ResponseEntity.ok(plist);
	}
	
}
