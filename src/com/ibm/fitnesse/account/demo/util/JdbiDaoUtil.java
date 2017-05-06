package com.ibm.fitnesse.account.demo.util;

import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbiDaoUtil {

	@Autowired
	private DBI jdbi;

	public <T> T getDaoByName(Class<T> clazz) {
		return jdbi.onDemand(clazz);
	}

}
