package com.netflix.conductor.config.auto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

@Configuration
@ConditionalOnProperty(prefix = "conductor", name = "enabled", havingValue = "true", matchIfMissing = true)
@ComponentScan(basePackages = {"com.netflix.conductor"})
public class ConductorAutoConfiguration {

    public ConductorAutoConfiguration(ConfigurableEnvironment environment) {
        try {
            ClassPathResource resource = new ClassPathResource("conductor-default.properties");
            ResourcePropertySource propertySource = new ResourcePropertySource(resource);
            environment.getPropertySources().addLast(propertySource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
