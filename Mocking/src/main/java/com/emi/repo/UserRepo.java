package com.emi.repo;

import java.util.List;

import com.emi.entity.User;

public interface UserRepo {

	User findById(int Id);
	List<User> getAllUser();
}
