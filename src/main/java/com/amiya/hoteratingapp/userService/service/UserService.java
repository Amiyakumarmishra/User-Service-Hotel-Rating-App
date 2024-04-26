package com.amiya.hoteratingapp.userService.service;

import java.util.List;

import com.amiya.hoteratingapp.userService.entity.UserEntity;

public interface UserService {
	
	List<UserEntity> getAllUsers();
	
	UserEntity getUserById(String userId);
	
	UserEntity createUser(UserEntity user);
} 
