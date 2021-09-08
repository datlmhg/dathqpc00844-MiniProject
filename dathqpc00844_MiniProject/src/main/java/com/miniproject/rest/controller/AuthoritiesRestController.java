package com.miniproject.rest.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.entity.Authority;
import com.miniproject.service.AccountService;
import com.miniproject.service.AuthorityService;
import com.miniproject.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthoritiesRestController {

	@Autowired
	AccountService account;
	
	@Autowired
	RoleService role;
	
	@Autowired
	AuthorityService authority;
	
	@GetMapping()
	public List<Authority> findAll() {
		return authority.findAll();
	}
	
	@PostMapping
	public Authority post(@RequestBody Authority auth) {
		return authority.create(auth);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authority.delete(id);
	}
	
	
}
