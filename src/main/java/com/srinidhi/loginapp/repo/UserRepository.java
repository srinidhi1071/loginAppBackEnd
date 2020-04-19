package com.srinidhi.loginapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srinidhi.loginapp.model.User;

public interface UserRepository  extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);

}