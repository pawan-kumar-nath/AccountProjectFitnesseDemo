package com.ibm.fitnesse.account.demo.config;

import org.springframework.context.ApplicationContext;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class AccountProjectFitnesseDemoApplication extends Application<AccountProjectFitnesseDemoConfiguration> {

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
        
        bootstrap.addBundle(new SwaggerBundle<AccountProjectFitnesseDemoConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AccountProjectFitnesseDemoConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
	}

	@Override
	public void run(AccountProjectFitnesseDemoConfiguration configuration, Environment environment) throws Exception {

		ApplicationContext applicationContext =
                springContextConfigurer.configure(configuration, environment.healthChecks(),environment);
        ApplicationConfigurer applicationConfigurer = applicationContext.getBean(ApplicationConfigurer.class);
        applicationConfigurer.configure(environment);

		environment.jersey().setUrlPattern("/fitnesse/demo/*");
	}

}
