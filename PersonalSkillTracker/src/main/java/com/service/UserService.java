package com.service;

import com.skillTracker.User;

public interface UserService 
{
	String register(User user);
    User login(String email, String password);

}
