package com.dedalus.service;

import com.dedalus.config.ApiKeyConfig;
import com.dedalus.model.NinjaAnimalModel;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@ApplicationScoped
public class NinjaAnimalService {
    @Inject
    ApiKeyConfig apiKeyConfig;

    @Inject
    @RestClient
    NinjaAnimalRestClient ninjaAnimalRestClient;
    @CacheResult(cacheName = "animal-cache")
    @Retry(maxRetries = 3, delay = 1, delayUnit = ChronoUnit.SECONDS)
    @Fallback(fallbackMethod = "fallback")
    public NinjaAnimalModel getNinjaAnimal(String name) {

        NinjaAnimalModel ninjaAnimalModel = ninjaAnimalRestClient.getNinjaAnimal(apiKeyConfig.getApiNinjaKey(), name).get(0);
        boolean valid = validateNinjaAnimal(ninjaAnimalModel);
        if(!valid){
            return null;
        }
        return ninjaAnimalModel;
    }

    public boolean validateNinjaAnimal(NinjaAnimalModel ninjaAnimalModel){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<NinjaAnimalModel>> violations = validator.validate(ninjaAnimalModel);
        for (ConstraintViolation<NinjaAnimalModel> violation : violations) {
            System.err.println(violation);
        }
        return violations.size() <= 0;
    }

    public NinjaAnimalModel fallback(String name){
        return null;
    }
}
