package com.dedalus.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class NinjaAnimalModel {
    @NotBlank
    @Size(min = 3)
    public String name;
    public NinjaAnimalCharacteristics characteristics;
    public NinjaAnimalTaxonomy taxonomy;
    public ArrayList<String> locations;
}
