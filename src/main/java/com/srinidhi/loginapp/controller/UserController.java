package com.srinidhi.loginapp.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.srinidhi.loginapp.exceptions.UserNameAlreadyPresentException;
import com.srinidhi.loginapp.model.User;
import com.srinidhi.loginapp.service.UserDAOService;



@RestController
@CrossOrigin(origins ="*")
public class UserController {

	@Autowired
	UserDAOService userDaoService;
	
	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity addUser(@RequestBody User user) {
		if(userDaoService.checkUserIsPresent(user.getUsername()))
			throw new UserNameAlreadyPresentException("UserName"+user.getUsername()+" Already Exists");
		User u = userDaoService.addUser(user);
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("msg","Added Successfully");
		
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@GetMapping("/signup/{username}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity CheckUserPresent(@PathVariable String username)
	{
		HashMap<String,Boolean> map = new HashMap<String,Boolean>();
		
		if(userDaoService.checkUserIsPresent(username)) {
//			throw new UserNameAlreadyPresentException("UserName "+username+" Already Exists");
			map.put("notFound",false);
			return new ResponseEntity<>(map, HttpStatus.CONFLICT);
		}
		
			map.put("notFound",true);
			return new ResponseEntity<>(map, HttpStatus.OK);
			
			
	}
}
