package com.springboot.jwt.security.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jwt.model.User;
import com.springboot.jwt.service.UserService;


@RestController
public class UserController {
	
	@Autowired private UserService userService;
	
	@GetMapping(value="/users")
	@PreAuthorize("hasAnyRole('Admin')")
	public ResponseEntity<List<User>> getAllUsers(){
		
		List<User> users=userService.findAll();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/forProduct")
	@PreAuthorize("hasAnyRole('Admin','Product')")
	public String forProduct() {
		return "THIS IS THE PRODUCT ROLE WE CAN ACCESS ADMIN AND PRODUCT";
	}
	
	
	@GetMapping(value="/forOrder")
	@PreAuthorize("hasAnyRole('Admin','Order')")
	public String forOrder() {
		return "THIS IS THE ONLY CAN ACCESS ADMIN AND ORDER ROLE...";
	}
	
	@GetMapping(value="/getuser")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public ResponseEntity<User> getUser(Principal principal){
		
		User users=userService.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(users,HttpStatus.OK);
		
	}

}
