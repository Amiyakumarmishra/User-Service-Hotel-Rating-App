package com.amiya.hoteratingapp.userService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amiya.hoteratingapp.userService.entity.UserEntity;
import com.amiya.hoteratingapp.userService.service.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity user) {
		
		return userService.createUser(user);
	}
	
	@GetMapping(value="/{id}")
//	@CircuitBreaker(name="ratingHotelCircuitBreaker",fallbackMethod="circuitBreakerMethod")
//	@Retry(name="ratingHotelRetry",fallbackMethod="reTryMethod")
	@RateLimiter(name="ratingHotelRateLimiter",fallbackMethod="rateLimiterMethod")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("id") String userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	public ResponseEntity<UserEntity> circuitBreakerMethod(String userId,Exception ex) {
		System.out.println(ex.getMessage());
		UserEntity user = new UserEntity();
		user.setAbout("Either hotel or rating service is down");
		return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<UserEntity> reTryMethod(String userId,Exception ex) {
		System.out.println(ex.getMessage());
		UserEntity user = new UserEntity();
		user.setAbout("Either hotel or rating service is down");
		return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<UserEntity> rateLimiterMethod(String userId,Exception ex) {
		System.out.println(ex.getMessage());
		UserEntity user = new UserEntity();
		user.setAbout("Either hotel or rating service is down");
		return ResponseEntity.ok(user);
	}
	
	
	
	@GetMapping
	public List<UserEntity> getAllUsers(){
		return userService.getAllUsers();
				
	}
}
