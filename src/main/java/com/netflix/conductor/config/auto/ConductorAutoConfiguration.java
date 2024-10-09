package com.netflix.conductor.config.auto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration class for Netflix Conductor auto-configuration.
 * <p>
 * This configuration will be automatically loaded if the 'conductor.enabled' property
 * is set to true or if it is not defined (default behavior).
 * It loads default property values from the 'conductor-default.properties' file into the Spring Environment.
 * </p>
 */
@Configuration
@ConditionalOnProperty(prefix = "conductor", name = "enabled", havingValue = "true", matchIfMissing = true)
@ComponentScan(basePackages = {"com.netflix.conductor"})
public class ConductorAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ConductorAutoConfiguration.class);

    /**
     * Constructor that loads the default Conductor properties into the Spring environment.
     *
     * @param environment the Spring {@link ConfigurableEnvironment} used to load properties
     */
    public ConductorAutoConfiguration(ConfigurableEnvironment environment) {
        try {
            // Load properties from the 'conductor-default.properties' file located in the classpath
            ClassPathResource resource = new ClassPathResource("conductor-default.properties");
            ResourcePropertySource propertySource = new ResourcePropertySource(resource);
            environment.getPropertySources().addLast(propertySource);
        } catch (Exception e) {
            // Log the error in case of an exception during loading properties
            logger.error("Failed to load conductor-default.properties", e);
        }
    }
}
