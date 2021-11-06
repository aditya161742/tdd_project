package com.tdd.serviceImpl;

import org.springframework.stereotype.Service;

import com.tdd.models.Users;
import com.tdd.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public Users addUser(Users user) {
		
		return new Users("abc","anj@nj.com",5);
	}
	
	public String getString() {
		return "abcccc";
	}

	
}
