package com.dedalus.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApiKeyConfig {

    @ConfigProperty(name = "API_NINJA_KEY")
    String apiNinjaKey;

    public String getApiNinjaKey() {
        return apiNinjaKey;
    }
}
