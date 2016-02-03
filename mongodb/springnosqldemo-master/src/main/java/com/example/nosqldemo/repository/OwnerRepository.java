package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Owner;

public interface OwnerRepository extends CrudRepository<Owner, ObjectId> {
	
	List<Owner> findByFname(String fname);
	
	List<Owner> findByLname(String lname);
	
	Owner findById(ObjectId id);

    List<Owner> findAll();

    @Query(value = "{ lname : {$regex: ?0 } }")
    List<Owner> findByLnamee(String lname);


}
