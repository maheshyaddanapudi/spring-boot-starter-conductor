package com.my.conductor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

public class ConductorPropertiesLoader implements EnvironmentPostProcessor {

    private static final String PROPERTY_SOURCE_NAME = "externalConductorProperties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String propertiesPath = environment.getProperty("spring.conductor.properties.path");

        if (!StringUtils.isEmpty(propertiesPath)) {
            // Load properties from the specified path
            Resource resource = new FileSystemResource(propertiesPath);
            loadPropertiesFromResource(resource, environment);
        }
    }

    private void loadPropertiesFromResource(Resource resource, ConfigurableEnvironment environment) {
        if (resource.exists()) {
            try {
                Properties properties = new Properties();
                properties.load(resource.getInputStream());
                // Add loaded properties to the Spring environment
                environment.getPropertySources().addLast(new PropertiesPropertySource(PROPERTY_SOURCE_NAME, properties));
                System.out.println("Loaded properties from: " + resource.getFilename());
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load properties from: " + resource, e);
            }
        }
    }
}
