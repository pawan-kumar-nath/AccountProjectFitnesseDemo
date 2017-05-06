package com.ibm.fitnesse.account.demo.resource;

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

import com.ibm.fitnesse.account.demo.service.CustomerService;

@Controller
@Path("/customer")
public class CustomerRestResource {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerService customerService;
	
	@POST
	@Path("/{customerName}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(@PathParam("customerName") String customerName) {
		LOG.info("Creating customer {0}",customerName);
		customerService.createCustomer(customerName);
        return Response.status(Response.Status.OK).build();
    }
}
