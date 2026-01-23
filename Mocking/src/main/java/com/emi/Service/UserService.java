package com.emi.Service;

import java.util.List;

import com.emi.entity.User;
import com.emi.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

	private final UserRepo userRepo;
	
	public String getUserNameInUpperCase(int id) {
		User user=userRepo.findById(id);
		
		if(user!=null) {
			return user.getName().toUpperCase();
		}else {
		return "Not available";
	}
		

	}
	
	public List<String> getUserPremium(){
		return userRepo.getAllUser()
				.stream()
				.filter(t -> t.isPremium())
				.map(u->u.getName())
				.toList();
	}
	
	public boolean checkforPremium(int id) {
		User user=userRepo.findById(id);
		
		if(user.isPremium()) {
			return true;
		}else {
			return false;
		}

	}

}
