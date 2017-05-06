package com.ibm.fitnesse.account.demo.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.fitnesse.account.demo.service.AccountService;
import com.ibm.fitnesse.account.demo.util.RestResponse;

@Controller
@Path("/account")
public class AccountRestResource {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;
	
	@POST
	@Path("/customer/{customerName}/deposit/amount/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposit(@PathParam("customerName") String customerName,
                               @PathParam("amount") int amount) {
		LOG.info("Depositing for customer {0} amount {1} ",customerName,amount);
        accountService.deposit(customerName, amount);
        return Response.status(Response.Status.OK).build();
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customer/{customerName}")
	public RestResponse getAccountBalance(@PathParam("customerName") String customerName) {
		LOG.info("Checking balance for customer {0} ",customerName);
		int balance = accountService.balance(customerName);
		RestResponse restResponse = new RestResponse();
		restResponse.setData(balance);
		return restResponse;
	}
	
}