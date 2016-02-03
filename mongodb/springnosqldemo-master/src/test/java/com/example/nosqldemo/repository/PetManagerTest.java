package com.example.nosqldemo.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.domain.Owner;
import com.example.nosqldemo.domain.Pet;
import com.example.nosqldemo.service.PetManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class PetManagerTest {

    @Autowired
    PetManager petManager;
    @Autowired
    OwnerRepository ownerManager;

    private final String fname1 = "fname1";
    private final String lname1 = "lname1";

    private final String fname2 = "fname2";
    private final String lname2 = "lname2";

    private List<ObjectId> allOwners = new ArrayList<ObjectId>();
    private List<ObjectId> allPet = new ArrayList<ObjectId>();

    @Before
    public void controlRequest() {

        Iterable<Owner> owners = ownerManager.findAll();
        Iterable<Pet> pets = petManager.findAllPet();
        for (Owner o : owners) {
            allOwners.add(o.getId());
        }
        for (Pet p : pets) {
            allOwners.add(p.getId());
        }
    }

    @After
    public void restoringDB() {
        Iterable<Owner> owners = ownerManager.findAll();
        Iterable<Pet> pets = petManager.findAllPet();
        boolean check;

        for (Owner o : owners) {
            check = true;
            if (allOwners.contains(o.getId())) {
                check = false;
            }
            if (check) {
            	ownerManager.delete(o);
            }
        }
        for (Pet p : pets) {
            check = true;
            if (allPet.contains(p.getId())) {
                check = false;
            }
            if (check) {
            	petManager.deletePet(p);
            }
        }
    }
    @Test
    public void checkFindOwnersInPet(){
        Pet pet = new Pet();
        pet.setName("name");

        Owner owner = new Owner();
        owner.setFname(fname1);
        owner.setLname(lname1);
        ownerManager.save(owner);

        Owner owner2 = new Owner();
        owner2.setFname(fname2);
        owner2.setLname(lname2);
        ownerManager.save(owner2);

        List<Owner> ownerList = new ArrayList<Owner>();

        ownerList.add(owner);
        ownerList.add(owner2);
        pet.setOwners(ownerList);
        petManager.addPet(pet);

        List<Owner> owners = petManager.getOwnersByPet(pet);

        assertEquals(owners.size(),2);
        assertEquals(owners,ownerList);
    }

    @Test
    public void checkDeleteOwnerbyPet(){
        Pet pet = new Pet();
        pet.setName("name");

        Owner owner = new Owner();
        owner.setFname(fname1);
        owner.setLname(lname1);
        ownerManager.save(owner);

        Owner owner2 = new Owner();
        owner2.setFname(fname1);
        owner2.setLname(lname1);
        ownerManager.save(owner2);

        List<Owner> ownerList = new ArrayList<Owner>();

        ownerList.add(owner);
        ownerList.add(owner2);
        pet.setOwners(ownerList);
        petManager.addPet(pet);

        petManager.deleteOwnerByPet(pet, owner2);

        List<Owner> owners = petManager.getOwnersByPet(pet);

        assertEquals(owners.size(),1);
        assertEquals(owners.get(0).getFname(),owner.getFname());
        assertEquals(owners.get(0).getLname(),owner.getLname());
        assertNull(ownerManager.findById(owner2.getId()));
    }

    @Test
    public void checkDeleteOwnersbyPetByLname() {
        Pet pet = new Pet();
        pet.setName("name");

        Owner owner = new Owner();
        owner.setFname(fname1);
        owner.setLname(lname1);
        ownerManager.save(owner);

        Owner owner1 = new Owner();
        owner1.setFname(fname2);
        owner1.setLname(lname2);
        ownerManager.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFname(fname1);
        owner2.setLname("qwerty");
        ownerManager.save(owner2);

        List<Owner> ownerList = new ArrayList<Owner>();

        ownerList.add(owner);
        ownerList.add(owner1);
        ownerList.add(owner2);
        pet.setOwners(ownerList);
        petManager.addPet(pet);
        petManager.deleteOwnersByPetByLname(pet, owner.getLname());
        List<Owner> owners = petManager.getOwnersByPet(pet);

        assertEquals(owners.size(), 2);
        assertTrue(owners.contains(owner2));
        assertFalse(owners.contains(owners));
        assertNull(ownerManager.findById(owner.getId()));
    }
}
