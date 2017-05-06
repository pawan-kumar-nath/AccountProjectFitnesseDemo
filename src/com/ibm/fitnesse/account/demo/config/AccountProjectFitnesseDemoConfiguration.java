package com.ibm.fitnesse.account.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AccountProjectFitnesseDemoConfiguration extends Configuration {

	@Valid
	@NotNull
	private Map<String, String> contextParameters = new HashMap<String, String>();

	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();


	public Map<String, String> getContextParameters() {
		return contextParameters;
	}

	public void setContextParameters(Map<String, String> contextParameters) {
		this.contextParameters = contextParameters;
	}

	@JsonProperty("database")
	public DataSourceFactory getDatabase() {
		return database;
	}

	@JsonProperty("database")
	public void setDatabase(DataSourceFactory mcpDatabase) {
		this.database = mcpDatabase;
	}
		
}
