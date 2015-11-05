package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Zwierze;

public class ZwierzeManagerTest {
	ZwierzeManager zwierzeManager = new ZwierzeManager();

	private final static String IMIE_1 = "Reksio";
	private final static String GATUNEK_1 = "Pies";
	private final static String DATA_PRZYJECIA_1 = "2015-09-22";
	private final static Long opiekun_id = (long) 1;
	private final static Long opiekun_id2 = (long) 2;
	private final static Long wlasciciel_id = (long) 1;

	@Test
	public void checkConnection(){
		assertNotNull(zwierzeManager.getConnection());
	}

	@Test
	public void checkAdd(){
		Zwierze zw = new Zwierze(IMIE_1, GATUNEK_1, DATA_PRZYJECIA_1, opiekun_id, wlasciciel_id);

		zwierzeManager.wyczyscZwierzeta();
		assertEquals(1, zwierzeManager.dodajZwierze(zw));

	}

	@Test
	public void checkUpdate(){
		Zwierze zw = new Zwierze(IMIE_1, GATUNEK_1, DATA_PRZYJECIA_1, opiekun_id, wlasciciel_id);

		zwierzeManager.wyczyscZwierzeta();
		assertEquals(1, zwierzeManager.dodajZwierze(zw));

		List<Zwierze> zwierzeta = zwierzeManager.GetZwierzeta();
		Zwierze zwierzeRetrieved = zwierzeta.get(0);

		zwierzeRetrieved.setOpiekun_id(opiekun_id2);

		assertEquals(1, zwierzeManager.updateZwierze(zwierzeRetrieved));

		List<Zwierze> zw2 = zwierzeManager.GetZwierzeta();
		Zwierze zwierzeRetrieved2 = zw2.get(0);

		assertEquals(1, zwierzeManager.updateZwierze(zwierzeRetrieved));
	}

	@Test
	public void checkDelete(){
		Zwierze zw = new Zwierze(IMIE_1, GATUNEK_1, DATA_PRZYJECIA_1, opiekun_id, wlasciciel_id);

		List<Zwierze> zwierze = zwierzeManager.GetZwierzeta();
		Zwierze zwierzeRetrieved = zwierze.get(0);
		assertEquals(1, zwierzeManager.usunZwierze(zwierzeRetrieved));

	}
}
