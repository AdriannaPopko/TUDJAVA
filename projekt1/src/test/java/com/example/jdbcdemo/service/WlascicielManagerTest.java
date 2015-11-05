package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Wlasciciel;

/*

public class WlascicielManagerTest {


	WlascicielManager wlascicielManager = new WlascicielManager();
	
	private final static String IMIE_1 = "Anka";
	private final static String NAZWISKO_1 = "Skakanka";
	private final static int NR_TEL_1 = 789789789;

	private final static String IMIE_2 = "Monika";
	private final static String NAZWISKO_2 = "Ratownika";
	private final static int NR_TEL_2 = 123123123;
	
	@Test
	public void checkConnection(){
		assertNotNull(wlascicielManager.getConnection());
	}

	@Test
	public void checkAdding(){

		Wlasciciel wlasciciel = new Wlasciciel(IMIE_1, NAZWISKO_1, NR_TEL_1);

		wlascicielManager.wyczyscWlascicieli();
		assertEquals(1,wlascicielManager.dodajWlasciciela(wlasciciel));
		
		List<Wlasciciel> wlasciciele = wlascicielManager.GetWlascicieli();
		Wlasciciel wlascicielRetrieved = wlasciciele.get(0);
		
		assertEquals(IMIE_1, wlascicielRetrieved.getImie());
		assertEquals(NAZWISKO_1, wlascicielRetrieved.getNazwisko());
		assertEquals(NR_TEL_1, wlascicielRetrieved.getNr());
		
	}


	@Test
	public void checkUpdate(){
		Wlasciciel wlasciciel = new Wlasciciel(IMIE_1, NAZWISKO_1, NR_TEL_1);

		wlascicielManager.wyczyscWlascicieli();
		assertEquals(1,wlascicielManager.dodajWlasciciela(wlasciciel));

		List<Wlasciciel> wlasciciele = wlascicielManager.GetWlascicieli();
		Wlasciciel wlascicielRetrieved = wlasciciele.get(0);

		wlascicielRetrieved.setImie(IMIE_2);
		wlascicielRetrieved.setNazwisko(NAZWISKO_2);
		wlascicielRetrieved.setNr(NR_TEL_2);


		assertEquals(1, wlascicielManager.updateWlasciciel(wlascicielRetrieved));

		List<Wlasciciel> wl = wlascicielManager.GetWlascicieli();
		Wlasciciel wlascicielRetrieved2 = wl.get(0);

		assertEquals(IMIE_2, wlascicielRetrieved2.getImie());
		assertEquals(NAZWISKO_2, wlascicielRetrieved2.getNazwisko());
		assertEquals(NR_TEL_2, wlascicielRetrieved2.getNr());
		assertEquals(wlascicielRetrieved2.getId(), wlascicielRetrieved2.getId());
	}

	@Test
	public void checkDelete() {
		Wlasciciel wlasciciel = new Wlasciciel(IMIE_1, NAZWISKO_1, NR_TEL_1);

		wlascicielManager.wyczyscWlascicieli();
		assertEquals(1,wlascicielManager.dodajWlasciciela(wlasciciel));

		List<Wlasciciel> wlasciciele = wlascicielManager.GetWlascicieli();
		Wlasciciel wlascicielRetrieved = wlasciciele.get(0);

		assertEquals(1, wlascicielManager.usunWlasciciela(wlascicielRetrieved));

	}

}
*/