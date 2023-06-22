package com.dedalus.service;
import com.dedalus.model.NinjaAnimalModel;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;

@ApplicationScoped
@RegisterRestClient(baseUri = "https://api.api-ninjas.com/v1/animals")
public interface NinjaAnimalRestClient {
    @GET
    NinjaAnimalModel getNinjaAnimal(@HeaderParam("X-Api-Key") String key, @QueryParam("name") String name);
}
