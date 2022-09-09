package com.nirma.userservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nirma.userservice.model.Bike;

@FeignClient(name = "bike-service" , url="http://localhost:8082/bike")
public interface BikeFeignClient {
	@PostMapping()
	Bike save(@RequestBody Bike bike);
	
	@GetMapping("/byuser/{userId}")
	List<Bike> getBikes(@PathVariable("userId")int userId );
}
