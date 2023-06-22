package com.dedalus.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class ApiKeyConfig {

    @ConfigProperty(name = "API_NINJA_KEY")
    public String apiNinjaKey;

}
