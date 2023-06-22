package com.dedalus.model;

import com.dedalus.entity.AnimalEntity;
import com.dedalus.entity.AnimalType;

import javax.validation.constraints.Min;

public class FullAnimalModel {

    public String name;

    public String animalType;

    public String comment;

    public boolean available;

    public Long id;

    public FullAnimalModel (AnimalEntity animalEntity) {
        this.animalType = animalEntity.animalType.toString();
        this.name = animalEntity.name;
        this.comment=animalEntity.comment;
        this.id= animalEntity.id;
        this.available= animalEntity.available;
    }
}
