package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Pet;
import com.example.shdemo.domain.Owner;

public interface SellingManager {
	
	void addOwner(Owner owner);
	List<Owner> getAllOwners();
	void deleteOwner(Owner owner);
	void updateOwner(Owner owner);
	Owner findOwnerById(Owner owner);
	Owner findOwnerBylastName(String lastName);
	List<Pet> findOwnerByPet(Owner owner);
	
	void addNewPet(Pet pet);
	void updatePet(Pet pet);
	void deletePet(Owner owner,Pet pet);
	List<Pet> getAllPets();
	Pet findPetById(Pet pet);
	List<Pet> findPetBySpecies(String species);
	void addPetToUser(Pet pet, Owner owner);

}
