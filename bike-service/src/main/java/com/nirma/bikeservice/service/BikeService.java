package com.nirma.bikeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirma.bikeservice.entity.Bike;
import com.nirma.bikeservice.repository.IBikeRepository;

@Service
public class BikeService {
	@Autowired
	IBikeRepository bikeRepository;
	
	public List<Bike> getAll(){
		return bikeRepository.findAll();
	}
	
	public Bike getUserById(int id) {
		return bikeRepository.findById(id).orElse(null);
	}
	
	public Bike save(Bike user) {
		Bike userNew= bikeRepository.save(user);
		return userNew;
	}
	public List<Bike> byUserId(int userId){
		return bikeRepository.findByUserId(userId);
	}
}
