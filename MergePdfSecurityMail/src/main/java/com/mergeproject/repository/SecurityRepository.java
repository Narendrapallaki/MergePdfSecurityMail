package com.mergeproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mergeproject.entity.User;



@Repository
public interface SecurityRepository extends JpaRepository<User, Integer> {

	
	 Optional<User> findByEmail(String email);
}
