package com.miniproject.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SignupRequest {

	private String username;
	private String password;
	private String fullname;
	private String email;
	private List<String> role;
	
}
