package com.explore.canada.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


/**
 * This is used to read in a properties file and setup access to the RESTServer bean/
 */

@Configuration
@ComponentScan("com.explore")
@PropertySource("classpath:application.properties")
public class RESTConfiguration
{

    @Bean
    public RESTServer restServer(Environment env)
    {
        return new RESTServer(
                env.getProperty("host.token"),
                env.getProperty("host.secret"),
                env.getProperty("host.address"),
                env.getProperty("host.port"));
    }
}
