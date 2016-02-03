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

import com.example.nosqldemo.domain.Pet;
import com.example.nosqldemo.domain.Owner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class OwnerManagerTest {

    @Autowired
    OwnerRepository ownerManager;

    private final String fname1 = "fname1";
    private final String lname1 = "lname1";

    private final String fname2 = "fname2";
    private final String lname2 = "lname2";

    private List<ObjectId> allOwners = new ArrayList<ObjectId>();

    @Before
    public void controlRequest() {

        Iterable<Owner> owners = ownerManager.findAll();

        for (Owner o : owners) {
            allOwners.add(o.getId());
        }
    }

    @After
    public void restoringDB() {
        Iterable<Owner> owners = ownerManager.findAll();

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
    }
    @Test
    public void checkAdding(){
    	Owner owner = new Owner();
    	owner.setFname(fname1);
    	owner.setLname(lname1);
        ownerManager.save(owner);

        Owner o = ownerManager.findById(owner.getId());

        assertFalse(allOwners.contains(o.getId()));
        assertEquals(o.getFname(),fname1);
        assertEquals(o.getLname(),lname1);
    }

    @Test
    public void checkUpdate(){
    	Owner owner = new Owner();
    	owner.setFname(fname1);
    	owner.setLname(lname1);
    	ownerManager.save(owner);

        Owner o = ownerManager.findById(owner.getId());

        assertEquals(fname1, o.getFname());
        assertEquals(lname1, o.getLname());

        o.setFname(fname2);
        o.setLname(lname2);
        ownerManager.save(o);

        o = ownerManager.findById(owner.getId());

        assertEquals(fname2, o.getFname());
        assertEquals(lname2, o.getLname());

        List<Owner> list = ownerManager.findAll();
        assertEquals(list.size(),allOwners.size()+1);

    }

    @Test
    public void checkFindByLname(){
        List<Owner> ownerList = ownerManager.findByLname("lastn");
        Owner owner = new Owner();
        owner.setFname(fname1);
        owner.setLname(lname1);
        ownerManager.save(owner);

        Owner owner2 = new Owner();
        owner2.setFname(fname2);
        owner2.setLname(lname2);
        ownerManager.save(owner2);

        List<Owner> ownerList2 = ownerManager.findByLname("lastn");
        assertEquals(ownerList2.size(),ownerList.size()+2);
    }

    @Test
    public void checkDelete(){
    	Owner owner = new Owner();
    	owner.setFname(fname1);
    	owner.setLname(lname1);
    	ownerManager.save(owner);
        ObjectId id = owner.getId();
        ownerManager.delete(owner);
        List<Owner> list = ownerManager.findAll();
        Owner o = ownerManager.findById(id);

        assertEquals(allOwners.size(),list.size());
        assertNull(o);
    }

    @Test
    public void findById() {
    	Owner owner = new Owner();
    	owner.setFname(fname1);
    	owner.setLname(lname1);
    	ownerManager.save(owner);

        Owner o = ownerManager.findById(owner.getId());

        assertEquals(o.getFname(), owner.getFname());
        assertEquals(o.getLname(), owner.getLname());
    }

    @Test
    public void findByFname(){
        List<Owner> ownerList = ownerManager.findByFname(fname1);

        Owner owner = new Owner();
        owner.setFname(fname1);
        owner.setLname(lname1);
        ownerManager.save(owner);

        List<Owner> ownerList1 = ownerManager.findByFname(fname1);

        assertEquals(ownerList1.size(),ownerList.size()+1);
    }
}

