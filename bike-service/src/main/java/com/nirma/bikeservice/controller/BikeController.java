package com.nirma.bikeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirma.bikeservice.entity.Bike;
import com.nirma.bikeservice.service.BikeService;

@RestController
@RequestMapping("/bike")
public class BikeController {

	@Autowired
	BikeService bikeService;
	
	@GetMapping
	public ResponseEntity<List<Bike> >getAll(){
		List<Bike> cars=bikeService.getAll();
		if(cars.isEmpty()) {
			return ResponseEntity.noContent().build();		
		}
		return ResponseEntity.ok(cars);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Bike >getById(@PathVariable("id") int id){
		Bike car=bikeService.getUserById(id);
		if(car ==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car);
	}
	
	@PostMapping
	public ResponseEntity<Bike >save(@RequestBody Bike user){
		Bike userNew=bikeService.save(user);
		if(userNew ==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userNew);
	}
	
	@GetMapping("/byuser/{userId}")
	public ResponseEntity<List<Bike> >getByUserId(@PathVariable("userId") int userId){
		List<Bike> cars=bikeService.byUserId(userId);
	/*	if(cars.isEmpty()) {
			return ResponseEntity.notFound().build();
		}*/
		return ResponseEntity.ok(cars);
	}
}
