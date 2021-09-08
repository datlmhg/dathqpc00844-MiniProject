package com.miniproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{

}
