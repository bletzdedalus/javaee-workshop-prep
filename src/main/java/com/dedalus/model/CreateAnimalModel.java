package com.dedalus.model;

import com.dedalus.entity.AnimalEntity;
import com.dedalus.entity.AnimalType;
import io.vertx.core.cli.annotations.Description;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.ws.rs.core.MediaType;

public class CreateAnimalModel {

    @NotBlank(message = "Please enter a name with at least 3 characters")
    @Size(min = 3)
    public String name;

    @Schema(enumeration = {"CAT", "DOG", "BIRD"})
    @NotBlank(message = "Please enter CAT, DOG or BIRD")
    public String animalType;

    @Schema(description = "Additional information to the animal")
    public String comment;

    @Schema(description = "True if the animal is still available")
    public boolean available;

    public AnimalEntity getAnimalEntity(){
        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.animalType = AnimalType.getAnimalType(this.animalType);
        animalEntity.available = this.available;
        animalEntity.comment = this.comment;
        animalEntity.name = this.name;
        return animalEntity;
    }
}


