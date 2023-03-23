package com.InfraMart.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Checkout 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long chkoutId;
	
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	@Column(nullable=false)
	private String address;
	
	@Column(nullable=false)
	private String city;
	
	@NotNull
	private int pinCode;
	
	@Column(nullable=false)
	private String contactNo;

	public Checkout() {
		super();
	}

	public long getChkoutId() {
		return chkoutId;
	}

	public void setChkoutId(long chkoutId) {
		this.chkoutId = chkoutId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

//	public Checkout(String firstName, String lastName, String address, String city, int pinCode, String contactNo) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.address = address;
//		this.city = city;
//		this.pinCode = pinCode;
//		this.contactNo = contactNo;
//	}
	
	
}
