package com.miniproject.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.miniproject.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	List<Order> findByUsername(String username);

	Object findById(Integer id);

}
