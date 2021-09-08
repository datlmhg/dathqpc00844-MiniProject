package com.miniproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

}
