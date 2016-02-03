package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Pet;

public interface PetRepository extends CrudRepository<Pet, ObjectId>{
	
	List<Pet> findByName(String name);
	
	List<Pet> findBySpecies(String species);
	
	@Query(value = "{ 'name' : ?0, 'species' : ?1 }" )
	List<Pet> znajdzZwierze(String name, String species);
	
	Pet findById(ObjectId id);

}
