package com.InfraMart.service;

import java.util.List;

import com.InfraMart.beans.Product;
import com.InfraMart.beans.User;

public interface UserService 
{
	public User findByEmail(String email);

	public User addNewUser(User u);

	public User isValid(User u);

	public List<User> getAll();

	public void deleteById(long userId);

	public int updateUserById(User u);

	public int updateById(User user);

	public User addTocart(List<Product> plist, String u);




}
