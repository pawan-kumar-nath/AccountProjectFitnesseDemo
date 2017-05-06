package com.ibm.fitnesse.account.demo.config;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.Path;
import javax.ws.rs.ext.ExceptionMapper;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import io.dropwizard.jersey.setup.JerseyEnvironment;

@Service
public class JerseyResourceRegistrar {

    private final ApplicationContext applicationContext;

    @Autowired
    public JerseyResourceRegistrar(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void registerResourcesWith(JerseyEnvironment jerseyEnvironment) {
        Map<String, Object> resources = applicationContext.getBeansWithAnnotation(Path.class);
        for (Map.Entry<String, Object> entry : resources.entrySet()) {
            jerseyEnvironment.register(entry.getValue());
        }
    }

    @SuppressWarnings("rawtypes")
	public void registerExceptionMapperWith(JerseyEnvironment jerseyEnvironment) {
        Reflections reflections = new Reflections("com.ibm.fitnesse.account.demo");
        Set<Class<? extends ExceptionMapper>> subTypes = reflections.getSubTypesOf(ExceptionMapper.class);

        for (Class<? extends ExceptionMapper> subType : subTypes) {
            jerseyEnvironment.register(subType);
        }
    }

}
