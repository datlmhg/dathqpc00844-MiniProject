package com.miniproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{

}
