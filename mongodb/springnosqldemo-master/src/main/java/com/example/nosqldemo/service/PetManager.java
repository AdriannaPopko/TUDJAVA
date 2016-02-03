package com.example.nosqldemo.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Owner;
import com.example.nosqldemo.domain.Pet;
import com.example.nosqldemo.repository.OwnerRepository;
import com.example.nosqldemo.repository.PetRepository;

@Component
public class PetManager {

    @Autowired
    private  PetRepository petRepository;
    @Autowired
    private OwnerRepository ownerRepository;


    public void addPet(Pet pet){ petRepository.save(pet); }
    public void deletePet(Pet pet){ petRepository.delete(pet);}
    public void updatePet(Pet pet, String species) {
    	pet.setSpecies(species);
    }
    public List<Owner> getPersInRoom(Pet pet) { return pet.getOwners();}
    public void deletePerInRoom(Pet pet,Owner owner){
    	pet.getOwners().remove(owner);
    	petRepository.save(pet);
        ownerRepository.delete(owner);
    }
    public Iterable<Pet> findAllPet() { return petRepository.findAll(); }
    public void deleteOwnersInPetByLname(Pet pet, String lname){
        List<Owner> ownerList = pet.getOwners();
        for(int i = 0; i < ownerList.size();i++){
            if(ownerList.get(i).getLname() == lname) {
                ObjectId id = ownerList.get(i).getId();
                ownerList.remove(i);
                ownerRepository.delete(id);
                i--;
            }
        }
        petRepository.save(pet);

    }
}
