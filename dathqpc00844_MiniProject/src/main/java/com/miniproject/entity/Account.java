package com.miniproject.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

	@Id
	String username;
	String password;
	String fullname;
	String email;
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
	List<Authority> authority;
	public Account(String username, String password, String fullname, String email) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
	}
}
