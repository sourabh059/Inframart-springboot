package com.InfraMart.service;

import java.util.List;

import com.InfraMart.beans.Category;

public interface CategoryService {

	Category addCategory(Category u);

	List<Category> getAllCats();

	void deleteByCatId(long categoryId);

	int updateCategoryById(Category cat);

	

}
