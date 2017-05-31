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
import com.ibm.fitnesse.account.demo.service.CustomerService;
import com.ibm.fitnesse.account.demo.util.RestResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/customer/{customerName}")
@Api("/customer/{customerName}")
public class CustomerRestResource {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create Customer", httpMethod = "POST", produces = MediaType.APPLICATION_JSON)
	public Response createCustomer(
			@ApiParam(value = "Customer Name", required = true) @PathParam("customerName") String customerName) {
		LOG.info("Creating customer {0}", customerName);
		customerService.createCustomer(customerName);
		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get account balance for given customer", httpMethod = "GET", produces = MediaType.APPLICATION_JSON, response = RestResponse.class)
	public RestResponse getCustomerAccountBalance(
			@ApiParam(value = "Customer Name", required = true) @PathParam("customerName") String customerName) {
		LOG.info("Checking balance for customer {0} ", customerName);
		int balance = accountService.balance(customerName);
		RestResponse restResponse = new RestResponse();
		restResponse.setData(balance);
		return restResponse;
	}
}
