package com.ibm.fitnesse.account.demo.service;

public interface AccountService {

	boolean deposit(String customerName, int amount);
	int balance(String customerName);
	
}
