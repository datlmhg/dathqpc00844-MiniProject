package com.miniproject.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.common.ERole;
import com.miniproject.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{
	
	Role findByName(ERole roleUser);
	
	@Query("SELECT DISTINCT r FROM Role r WHERE r.id IN('US', 'MOD')")
	List<Role> getRoles();

}
