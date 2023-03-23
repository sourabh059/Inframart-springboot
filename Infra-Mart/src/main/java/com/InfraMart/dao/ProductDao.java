package com.InfraMart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.InfraMart.beans.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>
{

}
