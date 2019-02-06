package com.sportoras.service.configuration;

import com.sportoras.database.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.sportoras.service")
@Import(DatabaseConfiguration.class)
public class ServiceConfiguration {
}