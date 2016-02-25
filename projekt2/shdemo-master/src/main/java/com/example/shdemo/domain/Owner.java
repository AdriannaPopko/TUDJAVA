package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({ 
	@NamedQuery(name = "owner.all", query = "Select o from Owner o"),
	@NamedQuery(name = "owner.bylastName", query = "Select o from Owner o where o.lastName = :lastName")
})
public class Owner {

	private Long id;

	private String firstName;
	private String lastName;

	private List<Pet> pets = new ArrayList<Pet>();

	public Owner(String firstName, String lastName){
		this.firstName=firstName;
		this.lastName=lastName;
	}
	public Owner(String firstName, String lastName, List<Pet> pet_id) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.pets=pet_id;
	}

	public Owner(Long id, String firstName, String lastName) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
	}
	public Owner(){
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Pet> getPets() {
		return pets;
	}
	
	public void setPets(List<Pet> pets) {
		this.pets =  pets;
	}
	
}