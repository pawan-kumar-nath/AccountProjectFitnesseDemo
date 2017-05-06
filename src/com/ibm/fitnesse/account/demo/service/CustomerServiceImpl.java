package com.ibm.fitnesse.account.demo.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fitnesse.account.demo.dao.CustomerDao;
import com.ibm.fitnesse.account.demo.exception.AccountDemoException;
import com.ibm.fitnesse.account.demo.util.JdbiDaoUtil;

@Service
public class CustomerServiceImpl implements CustomerService{

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbiDaoUtil jdbiDaoUtil;
	private CustomerDao customerDao;
	
	@PostConstruct
	public void postConstruct() throws Exception {
		customerDao = jdbiDaoUtil.getDaoByName(CustomerDao.class);
	}
	
	public int createCustomer(String customerName) {
		LOG.info("Creating customer by name ["+customerName+"]");
		int customerId = getCustomerId(customerName);
		if(customerId == 0){
			return customerDao.createCustomer(customerName);
		}else{
			throw new AccountDemoException("Customer ["+customerName+"] already exists");
		}
		
	}

	public int getCustomerId(String customerName) {
		LOG.info("Checking customer by name ["+customerName+"]");
		return customerDao.getCustomerId(customerName);
	}

}
