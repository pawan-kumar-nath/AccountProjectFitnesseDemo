package com.ibm.fitnesse.account.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;

@Service
public class ApplicationConfigurer {

    private final JerseyResourceRegistrar jerseyResourceRegistrar;

    @Autowired
    public ApplicationConfigurer(JerseyResourceRegistrar jerseyResourceRegistrar) {
    	this.jerseyResourceRegistrar = jerseyResourceRegistrar;
    }

    public void configure(Environment environment) throws Exception {
        JerseyEnvironment env = environment.jersey();
    	jerseyResourceRegistrar.registerResourcesWith(env);
        jerseyResourceRegistrar.registerExceptionMapperWith(env);
    }

}
