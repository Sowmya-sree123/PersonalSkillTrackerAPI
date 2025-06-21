package com.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.repository.UserRepository;
import com.skillTracker.User;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImp implements UserService
{
	@Autowired
    private UserRepository userRepository;

    @Override
    public String register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Email already registered!";
        }

        // Set createdAt date
        user.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // You can hash the password here in real apps
        userRepository.save(user);
        return "User registered successfully.";
    }

    @Override
    public User login(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(password)) {
            return existingUser.get();
        }
        return null;
    }

}
