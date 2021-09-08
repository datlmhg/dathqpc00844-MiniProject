package com.miniproject.rest.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.dto.SignupRequest;
import com.miniproject.entity.Account;
import com.miniproject.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {

	@Autowired
	AccountService accountService;
	
	@GetMapping()
	public List<Account> getAccounts() {
		return accountService.finAll();
	}
	
	@GetMapping("/signup")
	public List<Account> list() {
		return accountService.finAll();
	}
	
	@PostMapping("/signup")
	public Account create(@RequestBody SignupRequest signupData) {
		return accountService.create(signupData);
	}
	
	@PutMapping("/forgotpassword")
	public Account forgotpassword(@RequestBody Account account) {
		return accountService.forgotpassword(account);
	}
	
	@PutMapping("/changepassword")
	public Account changepassword(@RequestBody Account account) {
		return accountService.changepassword(account);
	}
	
	@PutMapping("/updateaccount")
	public Account updateaccount(@RequestBody Account account) {
		return accountService.updateaccount(account);
	}
	
}
