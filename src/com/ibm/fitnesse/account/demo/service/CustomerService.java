package com.ibm.fitnesse.account.demo.service;

public interface CustomerService {
	
	int createCustomer(String customerName);
	int getCustomerId(String customerName);
	
}
