package com.emi.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class CartService {

	HashMap<String , Integer> cart=new HashMap<>();
	
	
	public void addProdut(String item , Integer quantity) {
		if(quantity>0) {
			cart.put(item,quantity);
		}else {
			throw new IllegalArgumentException("Specify the required fields");
		}
	}
	
	public int getTotalProducts() {
		return cart.values().stream().mapToInt(Integer::intValue).sum();
    }
	
	public void clearCart() {
		cart.clear();
	}
	
	public void removeProduct(String item , Integer quantity) {
		if(cart.containsKey(item)) {
			cart.remove(item);
		}else {
			throw new IllegalArgumentException("No items to be deleted");
		}
	}
	
	public void increaseTheQuantity(String item , Integer quantity) {
		if(cart.containsKey(item)) {
			cart.put(item, quantity);
		}else {
			throw new IllegalArgumentException("please add the product before ipdating its quantity");
		}
	}
}
