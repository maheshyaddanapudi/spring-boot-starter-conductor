# Spring Boot Starter for Netflix Conductor

## Overview

This project is a Spring Boot Starter for Netflix Conductor that simplifies integrating Conductor with Spring Boot applications. It helps configure Netflix Conductor with minimal setup, leveraging Spring Boot’s dependency injection, configuration management, and auto-configuration capabilities.

## Prerequisites

Before using this starter, ensure you have the following:

	•	Java 17 or later: The project is built using Java 17, and you need at least Java 17 to run it.
	•	Spring Boot 2.7.x: The starter uses the Spring Boot BOM for version 2.7.x dependencies.

### Installation

#### Maven

To include the starter in your Maven project, add the following dependency to your pom.xml:

```xml
<dependency>
    <groupId>com.maheshyaddanapudi</groupId>
    <artifactId>spring-boot-starter-conductor</artifactId>
    <version>3.15.0</version>
</dependency>
```

#### Gradle

To include the starter in your Gradle project, add this line to your build.gradle:

```
implementation 'com.maheshyaddanapudi:spring-boot-starter-conductor:3.15.0'
```

## Using the Starter in a Client Application

Once you've added the `spring-boot-starter-conductor` dependency to your project, Netflix Conductor will be automatically configured and integrated into your Spring Boot application. This starter simplifies the process of starting up a Conductor server within the same JVM and application context as your Spring Boot application.

### How it Works

By default, the `spring-boot-starter-conductor` auto-configures a fully functional Conductor instance, enabling it to run within your Spring Boot application. This allows you to manage workflows, tasks, and queues from your application without needing to deploy and manage a separate Conductor server instance.

When your Spring Boot application starts, the following happens automatically:

1. **Conductor Components Initialization**: All necessary Conductor components such as the Conductor server, persistence layer, and task queues are initialized within the same JVM.
  
2. **Configuration Binding**: The starter reads configuration properties from your `application.properties` or `application.yml` file to customize Conductor’s behavior.

3. **Auto-configuration**: The starter integrates with Spring Boot’s auto-configuration mechanism, so all Conductor services (e.g., workflow execution, task management) are available as Spring beans, making them accessible throughout your Spring Boot application.

### Example of Spinning Up Conductor

1. **Add the Dependency**:
   Simply add the starter to your project as mentioned above (e.g., in Maven or Gradle).

2. **Running Conductor**:
   Once your application starts, the embedded Conductor server will also start. You can now manage workflows and tasks directly from within the same JVM and context as your Spring Boot application.

3. **Accessing Conductor APIs**:
   Since Conductor runs in the same JVM, you can interact with its API endpoints directly from your application or any HTTP client. For example:
   
   - **Swagger UI**: Visit `http://localhost:8080/api-docs` to view and interact with the Conductor API documentation via Swagger (unless overridden by client configurations).
   
   - **cURL Example**:
     ```bash
     # Example: Listing workflows via Conductor's REST API
     curl -X GET "http://localhost:8080/api/workflow" -H "accept: application/json"
     ```

By integrating Conductor into the same context, you have full access to manage and execute workflows and tasks programmatically within your Spring Boot application.

## Configuration

The Spring Boot Starter for Netflix Conductor comes with a set of default configurations to get you up and running quickly. You can override these defaults by specifying custom properties in your application.properties or application.yml file.

### Example Properties

Below are some common properties that can be overridden in your application.properties or application.yml file. For detailed configuration options, refer to the conductor-default.properties file in the resources folder.

```
# Enable or disable Netflix Conductor integration (enabled by default)
conductor.enabled=true

# API Docs configuration
springdoc.api-docs.path=/api-docs

# Sample data configuration
loadSample=true

# Conductor configurations
conductor.db.type=memory
```

For additional configurations like Redis, Elasticsearch, and more, please check the conductor-default.properties file located under the resources folder of this project.

### Disabling Conductor

If your application needs to run without Netflix Conductor, you can disable it with the following property:

```
conductor.enabled=false
```

When conductor.enabled=false, Conductor components will not be loaded into the Spring Boot context, and your application will function without Conductor.

## Published Artifacts

The following table lists the available artifacts published to Maven Central:

| Artifact ID                      | Description                                    | Maven Repository Link                                                                                                 |
|-----------------------------------|------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| `spring-boot-starter-conductor`   | Spring Boot starter for Netflix Conductor       | [Maven Central - spring-boot-starter-conductor](https://search.maven.org/artifact/com.maheshyaddanapudi/spring-boot-starter-conductor) |

## Building the Project

You can build the project locally using the following command:

```
./gradlew build
```

Testing Locally with Maven Local

To publish and test the starter locally, you can use Maven’s local repository:

```
./gradlew publishToMavenLocal
```

This will allow you to use the starter in other projects on your local machine by referencing it from your local Maven repository.

## Publishing to Maven Central

If you want to publish the starter to Maven Central, run the following command (ensure all the necessary configurations are set up):

```
./gradlew publish
```

## License

This project is licensed under the Apache License 2.0.

## Contributing

Contributions are welcome! If you find issues or have suggestions for improvements, please open an issue or a pull request on GitHub.
