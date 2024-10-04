package com.netflix.conductor.context.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

public class ConductorApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        try {
            // Load conductor-default.properties
            ClassPathResource resource = new ClassPathResource("conductor-default.properties");
            ResourcePropertySource propertySource = new ResourcePropertySource(resource);
            environment.getPropertySources().addLast(propertySource);
        } catch (Exception e) {
            // Handle the exception, logging if needed
            e.printStackTrace();
        }
    }
}