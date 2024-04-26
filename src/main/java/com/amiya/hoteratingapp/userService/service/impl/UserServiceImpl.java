package com.amiya.hoteratingapp.userService.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amiya.hoteratingapp.userService.entity.UserEntity;
import com.amiya.hoteratingapp.userService.exception.ResourceNotFoundException;
import com.amiya.hoteratingapp.userService.models.Hotel;
import com.amiya.hoteratingapp.userService.models.Rating;
import com.amiya.hoteratingapp.userService.remote.HotelService;
import com.amiya.hoteratingapp.userService.remote.RatingService;
import com.amiya.hoteratingapp.userService.repository.UserEntityRepository;
import com.amiya.hoteratingapp.userService.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserEntityRepository repo;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private HotelService hotelService;
	
	@Override
	public List<UserEntity> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public UserEntity getUserById(String userId) {
		// TODO Auto-generated method stub
		UserEntity user = repo.findById(userId).orElseThrow(()->  new ResourceNotFoundException("User with id "+userId+"not found"));
		
		List<Rating> ratings = ratingService.getRatingByUser(userId);
		
		for(Rating rating:ratings) {
			Hotel hotel = hotelService.getHotelByHotelId(rating.getHotelId());
			rating.setHotel(hotel);
		}
		user.setRatings(ratings);
		return user;
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		// TODO Auto-generated method stub
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		return repo.save(user);
	}

}
