package com.dedalus.service;

import com.dedalus.config.ApiKeyConfig;
import com.dedalus.model.NinjaAnimalModel;
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

    public NinjaAnimalModel getNinjaAnimal(String name) {

        NinjaAnimalModel ninjaAnimalModel = ninjaAnimalRestClient.getNinjaAnimal(apiKeyConfig.apiNinjaKey, name);
        return ninjaAnimalModel;
    }
}
