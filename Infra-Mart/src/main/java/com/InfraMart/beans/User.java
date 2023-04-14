package com.InfraMart.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userId;
	
	@Column(nullable = false)
	@NotBlank(message="user name cannot be empty!")
	@Size(min=2,max=20,message="name must be between 2-20 character")
	private String firstName;
	
	@Column(nullable = false)
	@NotBlank(message="user name cannot be empty!")
	@Size(min=2,max=20,message="name must be between 2-20 character")
	private String lastName;
	
	@Column(nullable = false, length = 50, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;
	
	private int active;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="userProduct",joinColumns=@JoinColumn(name="userId"),inverseJoinColumns=@JoinColumn(name="productId"))
	private List<Product> productlist;

	//this default constructor is enough to handle admin registration and user sign up
	public User() 
	{
		this.role = "user";
		this.active = 1;
	}	
	
//	public User(String email,String password) 
//	{
//		super();
//		this.email = email;
//		this.password = password;
//	}

//	public User(String firstName,String lastName,String email, String password) 
//	{
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password = password;
//		this.role = "user";
//		this.active = 1;
//		
//	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
	
	public List<String> getRoleList() {
		if (this.role.length() > 0) {
			return Arrays.asList(this.role.split(","));
		}

		return new ArrayList<String>();
	}
	
	
	
	
	
	
	
	
}