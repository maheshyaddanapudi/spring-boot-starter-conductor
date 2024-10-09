package com.netflix.conductor.context.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ApplicationContextInitializer implementation to load default Conductor properties into the Spring environment.
 */
public class ConductorApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final Logger logger = LoggerFactory.getLogger(ConductorApplicationContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        try {
            // Load conductor-default.properties
            ClassPathResource resource = new ClassPathResource("conductor-default.properties");
            ResourcePropertySource propertySource = new ResourcePropertySource(resource);
            environment.getPropertySources().addLast(propertySource);
        } catch (Exception e) {
            logger.error("Failed to load conductor-default.properties", e);
        }
    }
}
