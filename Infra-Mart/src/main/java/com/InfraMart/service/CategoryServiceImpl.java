package com.InfraMart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InfraMart.beans.Category;

import com.InfraMart.dao.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	CategoryDao cdao;

	@Override
	public Category addCategory(Category cat) {
		
		return cdao.save(cat);
	}

	@Override
	public List<Category> getAllCats() {
		
		return cdao.findAll();
	}

	@Override
	public void deleteByCatId(long categoryId) {
		
		cdao.deleteById(categoryId);
	}

	@Override
	public int updateCategoryById(Category cat) {
		
		Optional<Category> ctg=cdao.findById(cat.getCategoryId());
		if(ctg.isPresent())
		{
			Category catg=ctg.get();	
			catg.setCategoryName(cat.getCategoryName());
			cdao.save(catg);
			return 1;
		}
		return 0;
	}
	
	
}
