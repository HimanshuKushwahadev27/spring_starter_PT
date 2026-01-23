package com.emi.service;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartServiceTest {

	static CartService cartService;
		

	@BeforeAll	
	static void test() {
		cartService=new CartService();
		cartService.addProdut("Computers", 200);
		cartService.addProdut("pc", 100);
		cartService.addProdut("stringers", 50);
		cartService.addProdut("axes", 150);
	}
	
	@Test
	public void addProducts() {
		System.out.print("product added");	
	}
	
	@Test
	public void gettotalitems() {
		cartService.getTotalProducts();
	}
	
//	@Test
//	public void clearCarts() {
//		cartService.clearCart();
//	}
	
	@Test
	public void remove() {
		cartService.removeProduct("pc", 100);
	}
	
	@Test
	public void increase() {
		cartService.increaseTheQuantity("Computers", 100);
	}

}
