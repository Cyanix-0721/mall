package com.mole.common.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * This class is a configuration class for OkHttpClient.
 * It defines a bean that creates an OkHttpClient instance with specific configurations.
 */
@Configuration
public class OkHttpConfig {

    /**
     * This method creates an OkHttpClient instance with specific configurations.
     * The configurations include connection timeout, read timeout, write timeout, and connection pool settings.
     *
     * @return OkHttpClient instance with specific configurations
     */
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // Set the timeout for new connections to be established to 30 seconds
                .readTimeout(30, TimeUnit.SECONDS) // Set the timeout for data to be read from an established connection to 30 seconds
                .writeTimeout(30, TimeUnit.SECONDS) // Set the timeout for data to be written to an established connection to 30 seconds
                .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES)) // Set the connection pool to hold a maximum of 10 idle connections for 5 minutes
                .build();
    }
}