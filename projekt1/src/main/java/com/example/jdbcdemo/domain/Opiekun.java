package com.example.jdbcdemo.domain;

public class Opiekun {
	
	private long opiekun_id;
	
	private String imie;
	private String nazwisko;
	private int nr_tel;
	
	public Opiekun() {
	}
	
	public Opiekun(String imie, String nazwisko, int nr_tel) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nr_tel = nr_tel;
	}
	public long getId() {
		return opiekun_id;
	}
	public void setId(long id) {
		this.opiekun_id = opiekun_id;
	}
	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void getImie(String imie) {
		this.imie = imie;
	}

	public void getNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public int getNr() {
		return nr_tel;
	}
	public void getNr(int nr_tel) {
		this.nr_tel = nr_tel;
	}
	
}
