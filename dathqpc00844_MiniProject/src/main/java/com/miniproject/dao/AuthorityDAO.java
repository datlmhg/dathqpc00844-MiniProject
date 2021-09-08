package com.miniproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.entity.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Integer>{

}
