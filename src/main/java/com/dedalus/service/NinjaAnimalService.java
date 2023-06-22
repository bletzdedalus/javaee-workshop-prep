package com.dedalus.service;

import com.dedalus.config.ApiKeyConfig;
import com.dedalus.model.NinjaAnimalModel;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NinjaAnimalService {
    @Inject
    ApiKeyConfig apiKeyConfig;

    @Inject
    @RestClient
    NinjaAnimalRestClient ninjaAnimalRestClient;
    @CacheResult(cacheName = "weather-cache")
    public NinjaAnimalModel getNinjaAnimal(String name) {

        NinjaAnimalModel ninjaAnimalModel = ninjaAnimalRestClient.getNinjaAnimal(apiKeyConfig.getApiNinjaKey(), name).get(0);
        return ninjaAnimalModel;
    }
}
