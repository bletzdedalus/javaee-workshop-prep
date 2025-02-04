package com.dedalus.resources;

import com.dedalus.entity.AnimalEntity;
import com.dedalus.error.AlreadyAdoptedException;
import com.dedalus.error.AnimalNotFoundException;
import com.dedalus.error.InvalidAnimalTypeException;
import com.dedalus.error.InvalidIdentifierException;
import com.dedalus.model.CreateAnimalModel;
import com.dedalus.model.FullAnimalModel;
import com.dedalus.model.ListAnimalModel;
import com.dedalus.service.NinjaAnimalService;
import io.vertx.core.cli.annotations.Description;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/resource/animal")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalResource {
    @Inject
    NinjaAnimalService ninjaAnimalService;

    @POST
    @Description("Creates a new animal in database")
    @Path("create")
    public AnimalEntity createAnimal(@Valid CreateAnimalModel animalModel) throws InvalidAnimalTypeException {
        AnimalEntity animalEntity = animalModel.getAnimalEntity();
        if (animalEntity.animalType==null){
            throw new InvalidAnimalTypeException("Please enter only cats, dogs or birds");
        }

        animalEntity.persistAndFlush();
        return animalEntity;
    }

    @GET
    @Description("Lists all available animals")
    @Path("listAvailable")
    public List<ListAnimalModel> listAvailableAnimal() {
        List<AnimalEntity> animalEntities = AnimalEntity.listAvailable();
        List<ListAnimalModel> listAnimalModels = new ArrayList<>();
        for (AnimalEntity entity : animalEntities) {
            listAnimalModels.add(new ListAnimalModel(entity));
        }
        return listAnimalModels;
    }

    @GET
    @Description("Delivers all information to a animalID")
    @Path("{id}")
    public FullAnimalModel findSpecificAnimal( @PathParam("id") String id) throws AnimalNotFoundException {
        AnimalEntity animalEntity = AnimalEntity.findById(Long.valueOf(id));
        if(animalEntity == null){
            throw new AnimalNotFoundException("Animal not found");
        }
        FullAnimalModel fullAnimalModel = new FullAnimalModel(animalEntity);
        fullAnimalModel.setAdditionalInfo(ninjaAnimalService.getNinjaAnimal(fullAnimalModel.animalType));
        return fullAnimalModel;
    }

    @GET
    @Description("Sets the animal with the given ID as adopted ")
    @Path("/adopt/{id}")
    public FullAnimalModel adoptAnimal( @PathParam("id") String id) throws AlreadyAdoptedException, AnimalNotFoundException, InvalidIdentifierException {
        Long identifier;
        try {
            identifier = Long.valueOf(id);
        }catch (NumberFormatException ex){
            throw new InvalidIdentifierException("Identifier must be a Number greater than 0");
        }
        if(identifier<1) {
            throw new InvalidIdentifierException("Identifier must be a Number greater than 0");
        }
        AnimalEntity animalEntity = AnimalEntity.findById(identifier);
        if(animalEntity == null){
            throw new AnimalNotFoundException("Animal not found");
        }
        FullAnimalModel fullAnimalModel =new FullAnimalModel(animalEntity);

        if(fullAnimalModel.available){
            animalEntity.available=false;
            animalEntity.persistAndFlush();
            return fullAnimalModel;
        }
        throw new AlreadyAdoptedException("This animal is already adopted id: " + id);
    }
}