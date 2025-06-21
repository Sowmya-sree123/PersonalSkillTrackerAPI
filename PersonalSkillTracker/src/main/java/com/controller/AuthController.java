package com.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.LoginRequest;
import com.repository.UserRepository;
import com.service.UserService;
import com.skillTracker.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController 
{
	
	 @Autowired
	    private UserRepository userRepository;

	    // Registering the user
	    @PostMapping("/register")
	    public ResponseEntity<String> registerUser(@RequestBody User user) {
	        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists with this email");
	        } else {
	            userRepository.save(user);
	            return ResponseEntity.ok("User Registered Successfully!");
	        }
	    }

	    //Login
	    @PostMapping("/login")
	    public ResponseEntity<String> loginUser(@RequestBody User user, HttpSession session) {
	        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
	        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
	            session.setAttribute("userId", existingUser.get().getId());
	            return ResponseEntity.ok("Login successful.");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
	        }
	    }
}
