package com.example.nosqldemo.domain;

import java.util.List;

import org.bson.types.ObjectId;

public class Pet {

	private ObjectId id;
	private String name;
	private String species;
	private List<Owner> owners;


	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

    public List<Owner> getOwners(){
        return this.owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

}
