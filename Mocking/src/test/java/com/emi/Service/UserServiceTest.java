package com.emi.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.emi.entity.User;
import com.emi.repo.UserRepo;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock  // creating the mock obj
	private UserRepo userRepo;
	
	@InjectMocks  //injects the created mock into this 
	private UserService userService;
	
	@Test
	void testGetUserPremium() {
		User mockUser =  User.builder().name("Himanshu").id(101).premium(true).build();
		
		when(userRepo.findById(101)).thenReturn(mockUser );
		
		String name =userService.getUserNameInUpperCase(101);
		assertEquals("HIMANSHU" , name);
		
		//verify wether the method userRepo.findById(101) is being called in service or not 
		verify(userRepo, times(1)).findById(101);
	}

	@Test
	void testCheckforPremium() {
		List<User> mockUser=new ArrayList<>(List.of(User.builder().name("Himanshu").id(101).premium(true).build()
				,User.builder().name("Alex").id(102).premium(false).build()
				,User.builder().name("Ron").id(103).premium(true).build()
				,User.builder().name("zonin").id(104).premium(false).build()
				,User.builder().name("Morice").id(105).premium(true).build()
				,User.builder().name("Rebecca").id(106).premium(true).build()
				,User.builder().name("Alice").id(107).premium(false).build()));
		
		when(userRepo.getAllUser()).thenReturn(mockUser);
		List<String> name=userService.getUserPremium();
		
		assertEquals(4,name.size());
	}

	@Test
	void testUserService() {
		User mockUser =  User.builder().name("Himanshu").id(101).premium(true).build();
		
		when(userRepo.findById(101)).thenReturn(mockUser );
		
		boolean flag=userService.checkforPremium(101);
		
		assertEquals(true,flag);

	}

}
