package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.bitbucket.dollar.Dollar.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Pet;
import com.example.shdemo.domain.Owner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;

	private final String FNAME_1 = "Marcin";
	private final String LNAME_1 = "Szymanski";

	private final String FNAME_2 = "Kamil";
	private final String LNAME_2 = "Gonera";

	private final String NAME_1 = "Molly";
	private final String SPECIES_1 = "kot";

	private final String NAME_2 = "Arryn";
	private final String SPECIES_2 = "krolik";

	private List<Long> addedOwners = new ArrayList<Long>();
	private List<Long> addedPet = new ArrayList<Long>();

	@Before
	public void sprawdzDodaneElementy() {

		List<Owner> owners = sellingManager.getAllOwners();
		List<Pet> pets = sellingManager.getAllPets();

		for (Owner owner : owners)
			addedOwners.add(owner.getId());

		for (Pet pet : pets)
			addedPet.add(pet.getId());
	}

	@After
	public void usunTestowaneDane() {

		List<Owner> owners = sellingManager.getAllOwners();
		List<Pet> pets = sellingManager.getAllPets();

		boolean delete;
		for (Pet pet : pets) {
			delete = true;
			for (Long pet2 : addedPet)
				if (pet.getId() == pet2) {
					delete = false;
					break;
				}
			;
			if (delete)
				sellingManager.deletePet(pet);
		}

		for (Owner owner : owners) {
			delete = true;
			for (Long owner2 : addedOwners)
				if (owner.getId() == owner2) {
					delete = false;
					break;
				}
			if (delete)
				sellingManager.deleteOwner(owner);
		}

	}

	String validCharacters = $('0', '9').join() + $('A', 'Z').join();

	String randomString(int length) {
		return $(validCharacters).shuffle().slice(length).toString();
	}

	@Test
	public void addClientCheck() {

		int n = sellingManager.getAllOwners().size();
		Owner owner = new Owner(FNAME_1, LNAME_1);

		sellingManager.addOwner(owner);
		Owner retrievedOwner = sellingManager.findOwnerById(owner);
		assertEquals(owner.getId(), retrievedOwner.getId());
		assertEquals(FNAME_1, retrievedOwner.getfirstName());
		assertEquals(LNAME_1, retrievedOwner.getlastName());

		assertEquals(n + 1, sellingManager.getAllOwners().size());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void addClientRandomlyCheck() {
		int n = sellingManager.getAllOwners().size();

		Owner[] owners = new Owner[5];
		for (int i : $(5)) {
			owners[i] = new Owner();
			owners[i].setfirstName(randomString(6));
			owners[i].setlastName(randomString(12));
			sellingManager.addOwner(owners[i]);
		}
		for (int i: $(5)){
			Owner retrievedOwner = sellingManager.findOwnerById(owners[i]);
			assertEquals(owners[i].getId(), retrievedOwner.getId());
			assertEquals(owners[i].getfirstName(), retrievedOwner.getfirstName());
			assertEquals(owners[i].getlastName(), retrievedOwner.getlastName());
		}
		assertEquals(n + 5, sellingManager.getAllOwners().size());

		
		
	}

	@Test
	public void deleteClientCheck() {

		int n = sellingManager.getAllOwners().size();

		Owner owner = new Owner(FNAME_1, LNAME_1);

		sellingManager.addOwner(owner);

		Owner retrievedOwner = sellingManager.findOwnerById(owner);
		assertEquals(owner.getId(), retrievedOwner.getId());
		assertEquals(FNAME_1, retrievedOwner.getfirstName());
		assertEquals(LNAME_1, retrievedOwner.getlastName());

		assertEquals(n + 1, sellingManager.getAllOwners().size());
		sellingManager.deleteOwner(owner);
		assertEquals(n, sellingManager.getAllOwners().size());
	}

	@Test
	public void updateClientCheck() {
		Owner owner = new Owner(FNAME_1, LNAME_1);

		sellingManager.addOwner(owner);

		Owner retrievedOwner = sellingManager.findOwnerById(owner);
		assertEquals(owner.getId(), retrievedOwner.getId());
		assertEquals(FNAME_1, retrievedOwner.getfirstName());
		assertEquals(LNAME_1, retrievedOwner.getlastName());

		retrievedOwner.setfirstName(FNAME_2);
		retrievedOwner.setlastName(LNAME_2);
		sellingManager.updateOwner(retrievedOwner);

		Owner retrievedOwner2 = sellingManager.findOwnerById(retrievedOwner);
		assertEquals(retrievedOwner.getId(), retrievedOwner2.getId());
		assertEquals(FNAME_2, retrievedOwner2.getfirstName());
		assertEquals(LNAME_2, retrievedOwner2.getlastName());

	}

	@Test
	public void addPetCheck() {

		int n = sellingManager.getAllPets().size();

		Pet pet = new Pet(NAME_1, SPECIES_1);

		sellingManager.addNewPet(pet);

		Pet retrievedPet = sellingManager.findPetById(pet);
		assertEquals(NAME_1, retrievedPet.getName());
		assertEquals(SPECIES_1, retrievedPet.getSpecies());

		assertEquals(n + 1, sellingManager.getAllPets().size());
	}

	@Test
	public void deletePetCheck() {
		Pet pet = new Pet(NAME_1, SPECIES_1);

		sellingManager.addNewPet(pet);
		
		int n = sellingManager.getAllPets().size();

		Pet retrievedPet = sellingManager.findPetById(pet);
		assertEquals(pet.getId(), retrievedPet.getId());
		assertEquals(NAME_1, retrievedPet.getName());
		assertEquals(SPECIES_1, retrievedPet.getSpecies());

		sellingManager.deletePet(pet);
		assertEquals(n - 1, sellingManager.getAllPets().size());
	}

	@Test
	public void updatePetCheck() {
		Pet pet = new Pet(NAME_1, SPECIES_1);
		
		sellingManager.addNewPet(pet);

		Pet retrievedOwner = sellingManager.findPetById(pet);
		assertEquals(pet.getId(), retrievedOwner.getId());
		assertEquals(NAME_1, retrievedOwner.getName());
		assertEquals(SPECIES_1, retrievedOwner.getSpecies());

		retrievedOwner.setName(NAME_2);
		retrievedOwner.setSpecies(SPECIES_2);
		sellingManager.updatePet(retrievedOwner);

		Pet retrievedOwner2 = sellingManager.findPetById(retrievedOwner);
		assertEquals(retrievedOwner.getId(), retrievedOwner2.getId());
		assertEquals(NAME_2, retrievedOwner2.getName());
		assertEquals(SPECIES_2, retrievedOwner2.getSpecies());
	}

	@Test
	public void findPetBySpeciesCheck() {
		List<Pet> petSpecies = sellingManager.findPetBySpecies(SPECIES_1);
		int n = petSpecies.size();

		Pet pet = new Pet(NAME_1, SPECIES_1);

		sellingManager.addNewPet(pet);

		Pet retrievedPet = sellingManager.findPetById(pet);
		assertEquals(NAME_1, retrievedPet.getName());
		assertEquals(SPECIES_1, retrievedPet.getSpecies());

		Pet pet2 = new Pet(NAME_2, SPECIES_1);

		sellingManager.addNewPet(pet2);

		Pet retrievedPet2 = sellingManager.findPetById(pet2);
		assertEquals(NAME_2, retrievedPet2.getName());
		assertEquals(SPECIES_1, retrievedPet2.getSpecies());

		List<Pet> species = sellingManager.findPetBySpecies(SPECIES_1);

		for (Pet aPet : species) {
			assertEquals(aPet.getSpecies(), SPECIES_1);
		}

		assertEquals(n + 2, species.size());

	}

	@Test
	// szukanie wlascicieli o zadanym id zwierzecia
	public void addPetToOwnerCheck() {

		Pet pet = new Pet(NAME_1, SPECIES_1);

		sellingManager.addNewPet(pet);

		Pet retrievedPet = sellingManager.findPetById(pet);
		assertEquals(NAME_1, retrievedPet.getName());
		assertEquals(SPECIES_1, retrievedPet.getSpecies());

		Pet pet2 = new Pet(NAME_2, SPECIES_1);

		sellingManager.addNewPet(pet2);

		Pet retrievedPet2 = sellingManager.findPetById(pet2);
		assertEquals(NAME_2, retrievedPet2.getName());
		assertEquals(SPECIES_1, retrievedPet2.getSpecies());

		Owner owner = new Owner(FNAME_1, LNAME_1);

		sellingManager.addOwner(owner);

		Owner retrievedOwner = sellingManager.findOwnerById(owner);
		assertEquals(owner.getId(), retrievedOwner.getId());
		assertEquals(FNAME_1, retrievedOwner.getfirstName());
		assertEquals(LNAME_1, retrievedOwner.getlastName());

		
		List<Pet> ownerByPet = sellingManager.findOwnerByPet(retrievedOwner);
		int n = ownerByPet.size(); //ilosc petow w ownerze

		sellingManager.addPetToOwner(retrievedPet, retrievedOwner);
		sellingManager.addPetToOwner(retrievedPet2, retrievedOwner);

		assertEquals(n + 2, ownerByPet.size());

		for (Pet aPet : ownerByPet) {
			Pet pet3 = sellingManager.findPetById(aPet);
			assertEquals(aPet.getId(), pet3.getId());
			assertEquals(aPet.getName(), pet3.getName());
			assertEquals(aPet.getSpecies(), pet3.getSpecies());
		}
	}
	
	@Test
	// usuwanie wlascicieli o zadanym id zwierzecia
	public void checkRemovePetFromOwner() {

		Pet pet = new Pet(NAME_1, SPECIES_1);
		pet.setName(NAME_1);
		pet.setSpecies(SPECIES_1);

		sellingManager.addNewPet(pet);

		Pet retrievedPet = sellingManager.findPetById(pet);
		assertEquals(NAME_1, retrievedPet.getName());
		assertEquals(SPECIES_1, retrievedPet.getSpecies());

		Pet pet2 = new Pet(NAME_2, SPECIES_1);
		pet2.setName(NAME_2);
		pet2.setSpecies(SPECIES_1);

		sellingManager.addNewPet(pet2);

		Pet retrievedPet2 = sellingManager.findPetById(pet2);
		assertEquals(NAME_2, retrievedPet2.getName());
		assertEquals(SPECIES_1, retrievedPet2.getSpecies());

		Owner owner = new Owner(FNAME_1, LNAME_1);
		owner.setfirstName(FNAME_1);
		owner.setlastName(LNAME_1);

		sellingManager.addOwner(owner);

		Owner retrievedOwner = sellingManager.findOwnerById(owner);
		assertEquals(owner.getId(), retrievedOwner.getId());
		assertEquals(FNAME_1, retrievedOwner.getfirstName());
		assertEquals(LNAME_1, retrievedOwner.getlastName());

		List<Pet> ownerInPet = sellingManager.findOwnerByPet(retrievedOwner);
		int n = ownerInPet.size();

		sellingManager.addPetToOwner(retrievedPet, retrievedOwner);
		sellingManager.addPetToOwner(retrievedPet2, retrievedOwner);

		assertEquals(n + 2, ownerInPet.size());

		for (Pet aPet : ownerInPet) {
			Pet pet3 = sellingManager.findPetById(aPet);
			assertEquals(aPet.getId(), pet3.getId());
			assertEquals(aPet.getName(), pet3.getName());
			assertEquals(aPet.getSpecies(), pet3.getSpecies());
		}
		
		int m = sellingManager.getAllPets().size();
		int k = sellingManager.getAllOwners().size();

		sellingManager.deleteOwner(retrievedOwner);
		
		assertNull(sellingManager.findOwnerById(retrievedOwner));
		assertNull(sellingManager.findPetById(retrievedPet));
		assertNull(sellingManager.findPetById(retrievedPet2));
		assertEquals(k-1, sellingManager.getAllOwners().size());
		assertEquals(m-2, sellingManager.getAllPets().size());
	}
	
}