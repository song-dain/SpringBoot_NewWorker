package com.greedy.newworker.employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.newworker.common.ResponseDto;
import com.greedy.newworker.employee.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	

	
	
}
