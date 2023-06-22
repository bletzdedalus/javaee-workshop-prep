package com.dedalus.model;

import com.dedalus.entity.AnimalEntity;

public class ListAnimalModel {

    public String name;

    public String animalType;

    public ListAnimalModel (AnimalEntity animalEntity) {
        this.animalType = animalEntity.animalType.toString();
        this.name = animalEntity.name;
    }
}
