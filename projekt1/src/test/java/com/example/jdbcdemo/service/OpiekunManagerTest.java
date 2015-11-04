package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Opiekun;

public class OpiekunManagerTest {
	
	
	OpiekunManager opiekunManager = new OpiekunManager();
	
	private final static String IMIE_1 = "Aneta";
	private final static String NAZWISKO_1 = "Skarpeta";
	private final static int NR_TEL_1 = 512186646;

	private final static String IMIE_2 = "Ada";
	private final static String NAZWISKO_2 = "Takniewypada";
	private final static int NR_TEL_2 = 456456456;
	
	@Test
	public void checkConnection(){
		assertNotNull(opiekunManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		Opiekun opiekun = new Opiekun(IMIE_1, NAZWISKO_1, NR_TEL_1);

		opiekunManager.wyczyscOpiekunow();
		assertEquals(1,opiekunManager.dodajOpiekuna(opiekun));
		
		List<Opiekun> opiekunowie = opiekunManager.GetOpiekunow();
		Opiekun opiekunRetrieved = opiekunowie.get(0);
		
		assertEquals(IMIE_1, opiekunRetrieved.getImie());
		assertEquals(NAZWISKO_1, opiekunRetrieved.getNazwisko());
		assertEquals(NR_TEL_1, opiekunRetrieved.getNr());
		
	}

	@Test
	public void checkUpdate(){
		Opiekun opiekun = new Opiekun(IMIE_1, NAZWISKO_1, NR_TEL_1);

		opiekunManager.wyczyscOpiekunow();
		assertEquals(1,opiekunManager.dodajOpiekuna(opiekun));

		List<Opiekun> opiekunowie = opiekunManager.GetOpiekunow();
		Opiekun opiekunRetrieved = opiekunowie.get(0);

		opiekunRetrieved.setImie(IMIE_2);
		opiekunRetrieved.setNazwisko(NAZWISKO_2);
		opiekunRetrieved.setNr(NR_TEL_2);


		assertEquals(1, opiekunManager.updateOpiekun(opiekunRetrieved));

		List<Opiekun> op = opiekunManager.GetOpiekunow();
		Opiekun opiekunRetrieved2 = op.get(0);

		assertEquals(IMIE_2, opiekunRetrieved2.getImie());
		assertEquals(NAZWISKO_2, opiekunRetrieved2.getNazwisko());
		assertEquals(NR_TEL_2, opiekunRetrieved2.getNr());
		assertEquals(opiekunRetrieved2.getId(), opiekunRetrieved2.getId());
	}

	@Test
	public void checkDelete() {
		Opiekun opiekun = new Opiekun(IMIE_1, NAZWISKO_1, NR_TEL_1);

		opiekunManager.wyczyscOpiekunow();
		assertEquals(1,opiekunManager.dodajOpiekuna(opiekun));

		List<Opiekun> opiekunowie = opiekunManager.GetOpiekunow();
		Opiekun opiekunRetrieved = opiekunowie.get(0);

		assertEquals(1, opiekunManager.usunOpiekuna(opiekunRetrieved));

	}

}
