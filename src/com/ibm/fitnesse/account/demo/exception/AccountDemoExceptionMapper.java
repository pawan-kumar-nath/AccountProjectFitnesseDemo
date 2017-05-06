package com.ibm.fitnesse.account.demo.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.fitnesse.account.demo.util.RestResponse;

public class AccountDemoExceptionMapper implements ExceptionMapper<AccountDemoException> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public Response toResponse(AccountDemoException ex) {

    	RestResponse restResponse = new RestResponse(ex.getMessage());

        LOG.error(restResponse.toString(),ex);

        return Response.status(400)
            .entity(restResponse)
            .type(MediaType.APPLICATION_JSON)
            .build();
    }

}
