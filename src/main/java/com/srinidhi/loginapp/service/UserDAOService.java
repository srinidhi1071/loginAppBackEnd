package com.srinidhi.loginapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.srinidhi.loginapp.exceptions.UserNameAlreadyPresentException;
import com.srinidhi.loginapp.model.User;
import com.srinidhi.loginapp.repo.UserRepository;


@Component
public class UserDAOService {
	@Autowired
	public UserRepository usrRepo;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	public User addUser(User user)
	{
		
		
		User u = new User();
		u.setId((int)(usrRepo.count()+1));
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setUsername(user.getUsername());
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		u.setContact(user.getContact());
		u.setAddress(user.getAddress());
		u.setRole(user.getRole());
		usrRepo.save(u);
		return u;
	}

	public boolean checkUserIsPresent(String userName) {
		if(usrRepo.findByUsername(userName).isPresent())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	
}
