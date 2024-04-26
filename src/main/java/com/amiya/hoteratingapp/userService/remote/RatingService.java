package com.amiya.hoteratingapp.userService.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amiya.hoteratingapp.userService.models.Rating;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {
	
	@GetMapping("/rating/userId/{id}")
	List<Rating> getRatingByUser(@PathVariable String id);

}
