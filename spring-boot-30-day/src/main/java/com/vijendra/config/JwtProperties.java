package com.vijendra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.jwt")
@Component
public class JwtProperties {
    private String secret;
    private String expirationMs;

    public String getSecret() {
        return secret;
    }

    public String getExpirationMs() {
        return expirationMs;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setExpirationMs(String expirationMs) {
        this.expirationMs = expirationMs;
    }
}
