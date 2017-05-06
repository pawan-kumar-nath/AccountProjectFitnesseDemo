package com.ibm.fitnesse.account.demo.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AccountProjectFitnesseDemoConfiguration extends Configuration {

	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();

	@JsonProperty("database")
	public DataSourceFactory getDatabase() {
		return database;
	}

	@JsonProperty("database")
	public void setDatabase(DataSourceFactory mcpDatabase) {
		this.database = mcpDatabase;
	}
		
}
