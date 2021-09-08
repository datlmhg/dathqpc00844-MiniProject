package com.miniproject.service;

import java.util.List;

import com.miniproject.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Product findById(Integer id);

	List<Product> findByCategoryId(String string);

	

}
