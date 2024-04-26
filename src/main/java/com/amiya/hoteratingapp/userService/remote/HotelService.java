package com.amiya.hoteratingapp.userService.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amiya.hoteratingapp.userService.models.Hotel;

@FeignClient(name="HOTELSERVICE")
public interface HotelService {
	
	@GetMapping("/hotel/{id}")
	Hotel getHotelByHotelId(@PathVariable String id);

}
