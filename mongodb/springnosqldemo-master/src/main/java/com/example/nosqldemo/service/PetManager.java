package com.example.nosqldemo.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Pet;
import com.example.nosqldemo.repository.PetRepository;

@Component
public class PetManager {

	@Autowired
	private  PetRepository petRepository;
	
	public void addNewPet(Pet pet){
		petRepository.save(pet);
	}
	
	public List<Pet> getPets(String name){
		return petRepository.findByName(name);
	}
	
	public List<Pet> getPets(String name, String species){
		return petRepository.znajdzZwierze(name, species);
	}
	
	public Pet getPet(ObjectId id){
		return petRepository.findById(id);
	}
	
	
	
}
