package com.dedalus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NinjaAnimalTaxonomy {
    public String kingdom;
    public String phylum;
    @JsonProperty("class")
    public String myclass;
    public String order;
    public String family;
    public String genus;
    public String scientific_name;
}