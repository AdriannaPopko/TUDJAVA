package com.example.nosqldemo.domain;

import java.util.List;

import org.bson.types.ObjectId;

public class Owner {

	private ObjectId id;
	private String fname;
	private String lname;
	
	private List<Pet> pets;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	
	
}
