package com.my.conductor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConditionalOnBean;
import org.springframework.context.annotation.ConditionalOnProperty;
import org.springframework.context.support.GenericApplicationContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class ConductorStarterConfiguration {

    @Bean
    public void configureDatabaseBeans(GenericApplicationContext context, DataSource dataSource) {
        String dbType = determineDatabaseType(dataSource);
        registerDatabaseConfiguration(context, dbType);
    }

    private void registerDatabaseConfiguration(GenericApplicationContext context, String dbType) {
        switch (dbType.toLowerCase()) {
            case "mysql":
                context.registerBean(MySQLConfiguration.class);
                break;
            case "postgresql":
                context.registerBean(PostgresConfiguration.class);
                break;
            default:
                throw new IllegalStateException("Unsupported database type for Conductor: " + dbType);
        }
    }

    private String determineDatabaseType(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            String url = connection.getMetaData().getURL();
            if (url.startsWith("jdbc:mysql")) {
                return "mysql";
            } else if (url.startsWith("jdbc:postgresql")) {
                return "postgresql";
            } else {
                throw new IllegalStateException("Unsupported DataSource URL format: " + url);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error determining database type from DataSource", e);
        }
    }

    @Bean
    @ConditionalOnBean(name = "dataSource")
    @ConditionalOnProperty(name = "spring.conductor.startup.enabled", havingValue = "true", matchIfMissing = true)
    public Conductor conductor() {
        // Instantiate and configure Conductor
        return new Conductor();
    }
}
