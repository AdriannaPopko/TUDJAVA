package com.example.nosqldemo.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.domain.Car;
import com.example.nosqldemo.service.CarManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class CarManagerTest {
	
	@Autowired
	CarManager carManager;
	
	@Test
	public void checkAdding(){
		Car car = new Car();
		car.setMake("Ford");
		car.setModel("Fiesta");
		car.setYop(1998);
		carManager.addNewCar(car);
		
		Car car2 = new Car();
		car2.setMake("Ford");
		car2.setModel("Focus");
		car2.setYop(1998);
		carManager.addNewCar(car2);
		
		Car car3 = new Car();
		car3.setMake("Fiat");
		car3.setModel("Punto");
		car3.setYop(2011);
		carManager.addNewCar(car3);
		
		List<Car> cars = carManager.getCars("Ford");
		
		assertTrue(cars.size() >= 2);
	}

}
