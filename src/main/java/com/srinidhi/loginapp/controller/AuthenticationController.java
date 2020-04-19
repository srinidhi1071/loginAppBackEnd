package com.srinidhi.loginapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*","http://localhost:3000"})
public class AuthenticationController {
	
	@GetMapping(path = "/login")
	public ResponseEntity authenticate() {
		Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails ud = ((UserDetails)userPrincipal);
		String un =ud.getUsername();
		Collection<? extends GrantedAuthority> role = ud.getAuthorities();
		ArrayList al = new ArrayList(role);
		LinkedHashMap<String, String> map= new LinkedHashMap<>();
		map.put("msg","Successfully Logged In");
		map.put("username",un);
		map.put("role",String.valueOf(al.get(0)));
		return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
	}
}
