package com.ibm.fitnesse.account.demo.config;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AccountProjectFitnesseDemoApplication extends Application<AccountProjectFitnesseDemoConfiguration> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final SpringContextConfigurer springContextConfigurer;

	@Override
	public String getName() {
		return "account-service";
	}

	public AccountProjectFitnesseDemoApplication(SpringContextConfigurer springContextConfigurer) {
        this.springContextConfigurer = springContextConfigurer;
    }

    public AccountProjectFitnesseDemoApplication() {
        this(new SpringContextConfigurer());
    }

	public static void main(String[] args) throws Exception {
		new AccountProjectFitnesseDemoApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<AccountProjectFitnesseDemoConfiguration> bootstrap) {
		// Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        ObjectMapper mapper = bootstrap.getObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	@Override
	public void run(AccountProjectFitnesseDemoConfiguration configuration, Environment environment) throws Exception {

		setupServletEnvironment(configuration, environment);
		ApplicationContext applicationContext =
                springContextConfigurer.configure(configuration, environment.healthChecks(),environment);
        ApplicationConfigurer applicationConfigurer = applicationContext.getBean(ApplicationConfigurer.class);
        applicationConfigurer.configure(environment);

		environment.jersey().setUrlPattern("/account/*");
	}


	private void setupServletEnvironment(AccountProjectFitnesseDemoConfiguration configuration, Environment environment) {
		Map<String, String> contextParameters = configuration.getContextParameters();
		if(contextParameters != null && !contextParameters.isEmpty()) {
			for(String paramName : contextParameters.keySet()) {
				paramName = StringUtils.trimToNull(paramName);
				String paramValue = StringUtils.trimToNull(contextParameters.get(paramName));
				log.debug("Setting context parameter: " + paramName + "=" + paramValue);
				environment.getApplicationContext().setInitParameter(paramName, paramValue);
			}
		}
	}
}
