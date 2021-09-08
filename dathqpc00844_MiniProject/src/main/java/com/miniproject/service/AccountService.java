package com.miniproject.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.miniproject.dto.SignupRequest;
import com.miniproject.entity.Account;

public interface AccountService {

	Account findById(String username);

	List<Account> finAll();

	Account create(SignupRequest signupData);

	Account forgotpassword(Account account);

	Account changepassword(Account account);

	Account updateaccount(Account account);

}
