package com.nirma.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nirma.userservice.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	
}
