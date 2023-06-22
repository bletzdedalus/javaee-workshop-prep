package com.dedalus.entity;

public enum AnimalType {
    DOG,
    CAT,
    BIRD;

    public static AnimalType getAnimalType (String type){
        switch( type.toUpperCase()) {
            case "DOG":
                return AnimalType.DOG;
            case "CAT":
                return AnimalType.CAT;
            case "BIRD":
                return AnimalType.BIRD;
            default:
                return null;
        }
    }
}
