package com.nirma.bikeservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nirma.bikeservice.entity.Bike;

@Repository
public interface IBikeRepository extends JpaRepository<Bike, Integer> {
	List<Bike> findByUserId(int userId);
}
