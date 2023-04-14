package com.InfraMart.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long categoryId;
	
	@Column(nullable=false,unique = true)
	private String categoryName;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	private List<Product> productlist;
	
	
	public Category() 
	{
	
	}
	public Category(String categoryName) 
	{
		super();
		this.categoryName = categoryName;
	}
	
	public Category(int categoryId,String categoryName, List<Product> productlist) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.productlist = productlist;
	}

	public Category(long categoryId,String categoryName) 
	{
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}


	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/*
	 * public List<Product> getProductlist() { return productlist; }
	 * 
	 * public void setProductlist(List<Product> productlist) { this.productlist =
	 * productlist; }
	 */
	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName
				+ ", productlist=" /* + productlist */
				+ "]";
	}
}
