package com.miniproject.service.impl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.miniproject.common.ERole;
import com.miniproject.dao.AccountDAO;
import com.miniproject.dao.AuthorityDAO;
import com.miniproject.dao.RoleDAO;
import com.miniproject.dto.SignupRequest;
import com.miniproject.entity.Account;
import com.miniproject.entity.Authority;
import com.miniproject.entity.Role;
import com.miniproject.service.AccountService;
import com.miniproject.service.MailerService;



@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	AuthorityDAO authorityDAO;

	@Autowired
	MailerService mail;

	@Override
	public Account findById(String username) {
		return accountDAO.findById(username).get();
	}

	@Override
	public List<Account> finAll() {
		return accountDAO.findAll();
	}

	@Override
	public Account create(SignupRequest signupData) {
		Account account = new Account(signupData.getUsername(), signupData.getPassword(), signupData.getFullname(), signupData.getEmail());
		accountDAO.save(account);
		List<String> strRoles = signupData.getRole();
		List<Role> roles = new ArrayList<Role>();
		strRoles.forEach(role -> {
			switch(role) {
			case "admin":
				Role adminRole = roleDAO.findByName(ERole.ROLE_ADMIN);
				roles.add(adminRole);
				break;
			case "mod":
				Role modRole = roleDAO.findByName(ERole.ROLE_MODERATOR);
				roles.add(modRole);
				break;
			default:
				Role userRole = roleDAO.findByName(ERole.ROLE_USER);
				roles.add(userRole);
			}
		});
		roles.forEach(role -> {
			Authority authority = new Authority();
			authority.setAccount(account);
			authority.setRole(role);
			authorityDAO.save(authority);
		});
		return account;
	}

	@Override
	public Account forgotpassword(Account account) {
		String em = account.getEmail();
		try {
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                + "0123456789"
	                + "abcdefghijklmnopqrstuvxyz";
			StringBuilder sb = new StringBuilder(5);
			for (int i = 0; i < 5; i++) {
	            int index = (int)(AlphaNumericString.length() * Math.random());		  
	            sb.append(AlphaNumericString.charAt(index));
	        }
			String pass = sb.toString();
			account.setPassword(pass);
			accountDAO.save(account);
			mail.send(em, "Thành công", "Mật khẩu mới của bạn là: " + pass);
		} catch (Exception e) {
			System.out.println(e);
		}
		return account;
	}

	@Override
	public Account changepassword(Account account) {
		accountDAO.save(account);
		return account;
	}

	@Override
	public Account updateaccount(Account account) {
		accountDAO.save(account);
		return account;
	}
	
}
