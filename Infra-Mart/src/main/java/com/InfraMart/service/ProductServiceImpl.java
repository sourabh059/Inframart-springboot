package com.InfraMart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InfraMart.beans.Product;
import com.InfraMart.dao.ProductDao;

@Service
class ProductServiceImpl implements ProductService
{
	@Autowired 
	ProductDao pdao;

	
	@Override
	public Product addProduct(Product product) {
		//we have to give select tag in front end so we can select category and thereby categoryId
		
		return pdao.save(product);
	}

	@Override
	public List<Product> displayAllProducts() {
		
		return pdao.findAll();
	}

	@Override
	public void deleteByProductId(long productId) {
		
		pdao.deleteById(productId);
	}

	@Override
	public int updateByProductId(Product product) {
		Optional<Product> p=pdao.findById(product.getProductId());
		if(p.isPresent())
		{
			Product p1=p.get();
			//we have to show existing data on form page input box so that when we update specific fields it will not save null values
			p1.setProductName(product.getProductName());
			p1.setProductPrice(product.getProductPrice());
			p1.setProductUnit(product.getProductUnit());
//			p1.setImg(product.getImg());
//			p1.setCategory(product.getCategory());
			p1.setProductDescription(product.getProductDescription());
			pdao.save(p1);
			return 1;
		}
		return 0;
	}


	
}
