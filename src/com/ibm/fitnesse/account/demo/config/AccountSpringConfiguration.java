package com.ibm.fitnesse.account.demo.config;

import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;

@Configuration
@ComponentScan(basePackages = { "com.ibm.fitnesse.account.demo" })
public class AccountSpringConfiguration {

	@Autowired
	AccountProjectFitnesseDemoConfiguration accountProjectFitnesseDemoConfiguration;

	@Autowired
	Environment environment;

	@Bean
	public DBI geDatabaseJdbiSession() {
        final DBIFactory factory = new DBIFactory();
	    final DBI jdbi = factory.build(environment, accountProjectFitnesseDemoConfiguration.getDatabase(), "jdbiFactory");
	    return jdbi;
	}

}