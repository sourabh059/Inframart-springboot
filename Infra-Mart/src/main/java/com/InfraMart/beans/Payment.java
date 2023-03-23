package com.InfraMart.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Payment 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false,length=50)
	private String name;
	
	 @Column(nullable = false,length = 50)
	private long cardNum;
	
	 @Column(nullable = false,length = 50)
	private Date expiryDate;
	
	 @Column(nullable = false,length = 50)
	private int cvv;

	public Payment() 
	{
	
	}

	public Payment(String name,long cardNum,Date expiryDate,int cvv) {
		super();
		this.name = name;
		this.cardNum = cardNum;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCardNum() {
		return cardNum;
	}

	public void setCardNum(long cardNum) {
		this.cardNum = cardNum;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", name=" + name + ", cardNum=" + cardNum + ", expiryDate=" + expiryDate + ", cvv="
				+ cvv + "]";
	}

}
