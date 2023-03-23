package com.InfraMart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.InfraMart.beans.Product;
import com.InfraMart.beans.User;
import com.InfraMart.dao.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired 
	UserDao udao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//finds User by Email wherever needed(used in HomeController)
	@Override
	public User findByEmail(String email) {
		
		return udao.findByEmail(email);
	}

	//to Register user in HomeController (should add register method in both home and admin instead addNewUser)
	@Override
	public User addNewUser(User user) {
		
		
		user.setActive(1);
		user.setRegisterDate(new Date());

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setRole(user.getRole());
	
		return udao.save(user);
	}

	//to Check login credentials in HomeController
	@Override
	public User isValid(User u) {
		User u1=udao.findByEmail(u.getEmail());
		
		return u1;
	}
	
	//to get all users in AdminController
	@Override
	public List<User> getAll() {

		return udao.findAll();
	}
	
	//to Delete User,Admin,managers
	@Override
	public void deleteById(long userId) 
	{
		
		udao.deleteById(userId);
		
	}

	//to update user (only role) in AdminController
	@Override
	public int updateUserById(User u) 
	{
		Optional<User> op=udao.findById(u.getUserId());
		if(op.isPresent())
		{
			User user=op.get();	
			user.setRole(u.getRole());
//			user.setPassword(u.getPassword());  from forgot controller but not good idea
			udao.save(user);
			return 1;
		}
		return 0;
	}

	@Override
	public int updateById(User user) {
		if(user!=null) {
			udao.save(user);
			return 1;
		}
		return 0;
	}

	
	@Override
	public User addTocart(List<Product> plist, String u) 
	{
		/*
		 * Optional<User> u1=udao.findById(u.getUserId()); if(u.isPresent()) { User
		 * us=u.get(); us.setProductlist(plist);
		 * 
		 * udao.save(us); return 1; }
		 * 
		 * return 0;
		 */
		 System.out.println(u);
		User user=udao.findByEmail(u);
	
		long total=0;
		for(Product p:plist)
		{
			//Have to add quantity also and accordingly total bill should be returned
			//After plist gets added to user admin not able to retrieve allusers
			System.out.println(p.getProductPrice()+"------"+p.getProductUnit());
			total+=p.getProductPrice();
			System.out.println(p.getProductName());
//			System.out.println(p.getCart().getCost());
//			System.out.println(p.getCart().getQuantity());
		}
		if(user!=null)
		{ System.out.println(u);
			user.setProductlist(plist);
//			udao.save(user);
			System.out.println(u);
			return udao.save(user);
		}
		return null;
	}

	
	
	

}
