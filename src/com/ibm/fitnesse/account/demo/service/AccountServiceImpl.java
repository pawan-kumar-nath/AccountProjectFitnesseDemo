package com.ibm.fitnesse.account.demo.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fitnesse.account.demo.dao.AccountDao;
import com.ibm.fitnesse.account.demo.exception.AccountDemoException;
import com.ibm.fitnesse.account.demo.util.JdbiDaoUtil;

@Service
public class AccountServiceImpl implements AccountService{
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerService customerService;

	@Autowired
	private JdbiDaoUtil jdbiDaoUtil;
	private AccountDao accountDao;

	@PostConstruct
	public void postConstruct() throws Exception {
		accountDao = jdbiDaoUtil.getDaoByName(AccountDao.class);
	}
	
	public boolean deposit(String customerName, int amount) {

		boolean deposited = false;
		int customerId = customerService.getCustomerId(customerName);
		
		if(customerId != 0){
			int accountNumber = accountDao.getAccountNumberByCustomerId(customerId);
			if(accountNumber != 0){
				int currentBalance = accountDao.getBalance(customerId, accountNumber);
				accountDao.updateAccount(currentBalance + amount, accountNumber);
			}else{
				accountDao.createAccount(amount, customerId);
			}
			deposited = true;
		}else{
        	throw new AccountDemoException("Customer ["+customerName+"] does not exist");
		}
		
		LOG.info("Amount deposited ["+deposited+"] for customer ["+customerName+"]");
		return deposited;
	}

	public int balance(String customerName) {
		int customerId = customerService.getCustomerId(customerName);
		int balance = 0;
		if(customerId != 0){
			int accountNumber = accountDao.getAccountNumberByCustomerId(customerId);
			if(accountNumber != 0){
				balance = accountDao.getBalance(customerId, accountNumber);
			}else{
	        	throw new AccountDemoException("Account of customer ["+customerName+"] does not exist");
			}
		}else{
        	throw new AccountDemoException("Customer ["+customerName+"] does not exist");
		}
		return balance;
	}

	public boolean withdraw(String customerName, int amount) {
		boolean withdrawn = false;
		
		int customerId = customerService.getCustomerId(customerName);
		if(customerId != 0){
			int accountNumber = accountDao.getAccountNumberByCustomerId(customerId);
			if(accountNumber != 0){
				int currentBalance = accountDao.getBalance(customerId, accountNumber);
				if(currentBalance < amount){
		        	throw new AccountDemoException("Insufficient balance in account");
				}
				
				accountDao.updateAccount(currentBalance - amount, accountNumber);
				withdrawn = true;
			}else{
	        	throw new AccountDemoException("Account does not exist for customer ["+customerName+"]");
			}
		}else{
        	throw new AccountDemoException("Customer ["+customerName+"] does not exist");
		}
		
		LOG.info("Amount withdrawn ["+withdrawn+"] for customer ["+customerName+"]");

		return withdrawn;
	}

}
