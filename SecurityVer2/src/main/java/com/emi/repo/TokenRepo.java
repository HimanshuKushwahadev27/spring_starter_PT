package com.emi.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emi.entity.Token;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
	
	@Query("""
			select t from Token t 
			where t.user.user_Id = :user_Id and t.expired=false and t.revoked=false
			""")
	List<Token> findAllValidTokenByUser(Long user_Id);
	
	Optional<Token> findByToken(String token);


}
