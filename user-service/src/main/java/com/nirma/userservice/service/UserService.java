package com.nirma.userservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nirma.userservice.entity.User;
import com.nirma.userservice.feignclients.BikeFeignClient;
import com.nirma.userservice.feignclients.CarFeignClient;
import com.nirma.userservice.model.Bike;
import com.nirma.userservice.model.Car;
import com.nirma.userservice.repository.IUserRepository;

@Service
public class UserService {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CarFeignClient carFeignClient;
	
	@Autowired
	BikeFeignClient bikeFeignClient;
	
	
	@Autowired
	IUserRepository userRepository;
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User save(User user) {
		User userNew= userRepository.save(user);
		return userNew;
	}
	
	public List<Car> getCars(int userId){
		List<Car> cars = restTemplate.getForObject("http://localhost:8011/car/byuser/"+userId, List.class);
		return cars;
	}
	public List<Bike> getBykes(int userId){
		List<Bike> cars = restTemplate.getForObject("http://localhost:8182/bike/byuser/"+userId, List.class);
		return cars;
	}
	
	public Car saveCar(int idUser,Car car) {
		car.setUserId(idUser);
		Car newCar=carFeignClient.save(car);
		return newCar;
	}
	
	public Bike saveBike(Bike bike) {
		Bike newBike=bikeFeignClient.save(bike);
		return newBike;		
	}
	
	public Map<String,Object> getUserandVehicles(int userId){
		Map<String,Object> result= new HashMap<>();
		
		User user=userRepository.findById(userId).orElse(null);
		if (user==null) {
			result.put("Mensaje", "Usuario no existe");
		}else {
			result.put("User: ", user);
			List<Car> listCars=carFeignClient.getCars(userId);
			if (listCars==null||listCars.isEmpty()) {
				result.put("Cars", "Usuario no tiene carros");
			}else {
				result.put("Cars", listCars);
			}
			List<Bike> listBikes=bikeFeignClient.getBikes(userId);
			if (listBikes==null||listBikes.isEmpty()) {
				result.put("Bikes", "Usuario no tiene bicicletas");
			}else {
				result.put("Bikes", listBikes);
			}
		}
		
		
		return result;
	}
}
