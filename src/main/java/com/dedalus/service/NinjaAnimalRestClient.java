package com.dedalus.service;
import com.dedalus.model.NinjaAnimalModel;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import java.util.List;

@ApplicationScoped
@RegisterRestClient(baseUri = "https://api.api-ninjas.com/v1/animals")
public interface NinjaAnimalRestClient {
    @GET
    List<NinjaAnimalModel> getNinjaAnimal(@HeaderParam("X-Api-Key") String key, @QueryParam("name") String name);


}
