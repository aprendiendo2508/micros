package com.nirma.carservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirma.carservice.entity.Car;
import com.nirma.carservice.repository.ICarRepository;

@Service
public class CarService {
	@Autowired
	ICarRepository carRepository;
	
	public List<Car> getAll(){
		return carRepository.findAll();
	}
	
	public Car getUserById(int id) {
		return carRepository.findById(id).orElse(null);
	}
	
	public Car save(Car user) {
		Car userNew= carRepository.save(user);
		return userNew;
	}
	public List<Car> byUserId(int userId){
		return carRepository.findByUserId(userId);
	}
}
