package com.dedalus.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AnimalEntity extends PanacheEntity {

    public String name;

    public AnimalType animalType;

    public String comment;

    public boolean available;

    public static List<AnimalEntity> listAvailable(){
        List<AnimalEntity> animalList = AnimalEntity.list("available", true);
        return animalList;
    }
}
