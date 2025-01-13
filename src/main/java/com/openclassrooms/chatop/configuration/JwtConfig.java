package com.openclassrooms.chatop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${JWT_SECRET}")
    public String jwtSecret;

    public String getJwtSecret() {
        if (jwtSecret == null || jwtSecret.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET is not defined in the .env file!");
        }
        return jwtSecret;
    }
}
