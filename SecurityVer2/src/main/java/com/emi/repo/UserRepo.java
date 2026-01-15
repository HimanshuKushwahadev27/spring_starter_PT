package com.emi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emi.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User , Long> {

	//user can be in the DB or not 
	Optional<User> findByEmail(String email);
}
