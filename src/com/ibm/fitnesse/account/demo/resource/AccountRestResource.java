package com.ibm.fitnesse.account.demo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.fitnesse.account.demo.exception.AccountDemoException;
import com.ibm.fitnesse.account.demo.service.AccountService;
import com.ibm.fitnesse.account.demo.vo.CustomerAccount;

@Controller
@Path("/account")
public class AccountRestResource {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	private static final String DEPOSIT = "deposit";
	private static final String WITHDRAW = "withdraw";

	@Autowired
	private AccountService accountService;
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response operation(@QueryParam("operation") String operation, CustomerAccount customerAccount) {
		LOG.info("Performing operation {0} ",operation);
		
		if(DEPOSIT.equals(operation)){
	        accountService.deposit(customerAccount.getCustomerName(), customerAccount.getAmount());
		}else if(WITHDRAW.equals(operation)) {
			accountService.withdraw(customerAccount.getCustomerName(), customerAccount.getAmount());
		}else{
			throw new AccountDemoException("Invalid operation ["+operation+"]");
		}
	
        return Response.status(Response.Status.OK).build();
    }

}
