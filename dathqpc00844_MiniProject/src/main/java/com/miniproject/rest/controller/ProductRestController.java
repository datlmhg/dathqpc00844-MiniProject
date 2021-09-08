package com.miniproject.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.entity.Product;
import com.miniproject.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/list")
	public List<Product> list() {
		return productService.findAll();
	}
	
	@GetMapping("/{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}
	
}
