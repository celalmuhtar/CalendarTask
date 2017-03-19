package com.example.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.User;
import com.example.domain.UserRepository;

@Component
public class UserService{
	
	@Autowired
	private UserRepository rep;
	
	private Logger log = Logger.getLogger(UserService.class);

	public void insertUser(User user) {
		rep.save(user);
		log.info("Melumat elave eidildi");
	}
	
	public User findUserByUsername(String username){
	   return rep.findByUsername(username);
	}
}
