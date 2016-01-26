package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "pet.species", query = "Select p from Pet p where p.species = :species"),
		@NamedQuery(name = "pet.all", query = "Select p from Pet p")
})
public class Pet {

	private Long id;
	private String name;
	private String species;
	
	public Pet(){
		
	}
	public Pet(String name, String species) {
		this.name=name;
		this.species=species;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

}
