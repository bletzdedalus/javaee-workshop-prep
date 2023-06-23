package com.dedalus.service;

import com.dedalus.config.ApiKeyConfig;
import com.dedalus.error.InvalidApiResultException;
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
        try {
            validateNinjaAnimal(ninjaAnimalModel);
        } catch (InvalidApiResultException e) {
            e.printStackTrace();
            return null;
        }
        return ninjaAnimalModel;
    }

    public void validateNinjaAnimal(NinjaAnimalModel ninjaAnimalModel) throws InvalidApiResultException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<NinjaAnimalModel>> violations = validator.validate(ninjaAnimalModel);
        StringBuilder violationsString = new StringBuilder();
        for (ConstraintViolation<NinjaAnimalModel> violation : violations) {
            violationsString.append(violation.getMessage()).append("\n");
        }
        if(!violationsString.toString().isEmpty()){
            throw new InvalidApiResultException(violationsString.toString());
        }
    }

    public NinjaAnimalModel fallback(String name){
        return null;
    }
}
