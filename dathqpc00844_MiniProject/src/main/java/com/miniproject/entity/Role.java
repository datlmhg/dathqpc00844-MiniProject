package com.miniproject.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miniproject.common.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

	@Id
	String id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<Authority> authority;
	
}
