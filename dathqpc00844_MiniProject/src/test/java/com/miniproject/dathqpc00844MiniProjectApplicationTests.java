package com.miniproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.miniproject.dao.OrderDAO;
import com.miniproject.dao.OrderDetailDAO;
import com.miniproject.dto.SignupRequest;
import com.miniproject.entity.Account;
import com.miniproject.entity.Order;
import com.miniproject.entity.OrderDetail;
import com.miniproject.entity.Product;
import com.miniproject.rest.controller.AccountRestController;
import com.miniproject.service.AccountService;
import com.miniproject.service.OrderService;
import com.miniproject.service.ProductService;

@SpringBootTest
class dathqpc00844MiniProjectApplicationTests {

	@Autowired
	AccountRestController accountRestController;

	@Autowired
	AccountService accountService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	OrderDetailDAO orderDetailDAO;

	@Test
	void signUp1() {
		SignupRequest signuprRequest = new SignupRequest();
		List<String> role = new ArrayList<String>();
		role.add("US");
		signuprRequest.setUsername("QuocDat");
		signuprRequest.setPassword("123");
		signuprRequest.setFullname("Hồ Quốc Đạt");
		signuprRequest.setEmail("lolhunter1990@gmail.com");
		signuprRequest.setRole(role);
		accountRestController.create(signuprRequest);
		assertNotNull(accountService.findById(signuprRequest.getUsername()));
	}

	@Test
	void changePassword() {
		Account account = accountService.findById("HoQuocDat");
		account.setPassword("123");
		accountRestController.changepassword(account);
		assertNotEquals("abcd", accountService.findById("HoQuocDat").getPassword());
	}

	@Test
	void forgotPassword() {
		Account account = accountService.findById("QuocDat");
		accountRestController.forgotpassword(account);
		assertNotEquals(account.getPassword(), accountService.findById("HoQuocDat").getPassword());
	}

	@Test
	void updateAccount() {
		Account account = accountService.findById("QuocDat");
		account.setFullname("Quốc Đạt");
		accountRestController.updateaccount(account);
		assertNotEquals(account.getFullname(), accountService.findById("QuocDat"));
	}

	@Test
	void order() {
		Order order = new Order();
		OrderDetail orderDetail = new OrderDetail();
		Account account = accountService.findById("QuocDat");
		Product product = productService.findById(1);
		order.setCreateDate(new Date());
		order.setAddress("Hậu Giang");
		order.setAccount(account);
		orderDAO.save(order);
		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setPrice(product.getPrice());
		orderDetail.setQuantity(1);
		orderDetailDAO.save(orderDetail);
	}

	@Test
	void orderByUsername() {
		List<Order> order = orderService.findByUsername("QuocDat");
		assertThat(order).size().isGreaterThan(0);
	}

}
