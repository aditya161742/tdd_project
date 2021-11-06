package com.tdd.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.models.Users;
import com.tdd.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public String testHello() {
		
		return userService.getString();
		
	
	}
	
	@PostMapping(path = "/testPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void testPost(@RequestBody Map<String, Object> payloadO) throws Exception {
		
		System.out.println("Test post called..");
		System.out.println(payloadO.get("accountType"));
		
    }
	
	@PostMapping(path = "/testJsonReturn", consumes = MediaType.APPLICATION_JSON_VALUE)
	
    public Users testJsonReturn(@RequestBody Map<String, String> payloadO) throws Exception {
		
		
		System.out.println("Test json post called..");
		System.out.println(payloadO);
		
		Users users = new Users();
		users.setUsername(payloadO.get("username"));
		users.setEmailId(payloadO.get("email_id"));
		users.setBalance(Float.parseFloat(payloadO.get("balance")));
		
		return users;
		
    }
	
	//@PostMapping(path = "/testAddUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping("/testAddUser")
    public ResponseEntity<Users> testAdd(@RequestBody Users user) throws Exception {
		//ObjectMapper mapper = new ObjectMapper();
		//Users users = mapper.convertValue(payloadO, Users.class);  
		System.out.println("from add user = "+user.getUsername());
		System.out.println(userService.addUser(user));
		System.out.println(userService);
		
		try {
			user = userService.addUser(user);
			//System.out.println("country name = "+user.getEmailId());
			return new ResponseEntity<Users>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
    }
}
