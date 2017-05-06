package com.ibm.fitnesse.account.demo.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.codahale.metrics.health.HealthCheckRegistry;

import io.dropwizard.setup.Environment;

public class SpringContextConfigurer {


    public ApplicationContext configure(AccountProjectFitnesseDemoConfiguration accountProjectFitnesseDemoConfiguration, HealthCheckRegistry healthCheckRegistry,
                                        Environment environment) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setParent(startParentContext(accountProjectFitnesseDemoConfiguration, healthCheckRegistry,environment));
        applicationContext.register(AccountSpringConfiguration.class);
        applicationContext.refresh();
        applicationContext.registerShutdownHook();
        applicationContext.start();

        return applicationContext;
    }

    private ApplicationContext startParentContext(AccountProjectFitnesseDemoConfiguration accountProjectFitnesseDemoConfiguration, HealthCheckRegistry healthCheckRegistry,
                                                  Environment environment) {
        AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
        parent.refresh();
        parent.getBeanFactory().registerSingleton("configuration", accountProjectFitnesseDemoConfiguration);
        parent.getBeanFactory().registerSingleton("environmentObject",environment);
        parent.getBeanFactory().registerSingleton("healthCheckRegistry", healthCheckRegistry);
        parent.registerShutdownHook();
        parent.start();

        return parent;
    }
}
