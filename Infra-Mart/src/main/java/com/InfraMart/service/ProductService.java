package com.InfraMart.service;

import java.util.List;


import com.InfraMart.beans.Product;

public interface ProductService {

	List<Product> displayAllProducts();

	Product addProduct(Product product);

	void deleteByProductId(long productId);

	int updateByProductId(Product product);


	

}
