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

}
