package com.example.nosqldemo.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.domain.Car;
import com.example.nosqldemo.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class PersonManagerTest {
	
	@Autowired
	PersonRepository personManager;
	@Autowired
	CarRepository carRepository;
	
	@Test
	public void checkAdding(){
		
		Person p1 = new Person();
		p1.setName("Gosia");
		
		Car c1 = new Car();
		c1.setMake("Toyota");
		c1.setModel("Yaris");
		carRepository.save(c1);
		
		
		Car c2 = new Car();
		c2.setMake("Honda");
		c2.setModel("Accord");
		carRepository.save(c2);
		
		List<Car> cars = new ArrayList<Car>();
		cars.add(c1);
		cars.add(c2);
		
		p1.setCars(cars);
		
		
		personManager.save(p1);
		
		Person aGirl = personManager.findById(p1.getId());
		
		assertEquals(2, aGirl.getCars().size());
		
		
	}

}
