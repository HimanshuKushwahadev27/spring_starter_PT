package com.emi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emi.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query("""
			SELECT u from User u 
			WHERE u.email= :email
			""")
	public Optional<User> findByEmail(String email);

}
