package com.example.jdbcdemo.domain;

public class Wlasciciel {

    private long wlasciciel_id;

    private String imie;
    private String nazwisko;
    private int nr_tel;

    public Wlasciciel() {
    }

    public Opiekun(String imie, String nazwisko, int nr_tel) {
        super();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_tel = nr_tel;
    }
    public long getId() {
        return wlasciciel_id;
    }
    public void setId(long id) {
        this.wlasciciel_id = id;
    }
    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getNr() {
        return nr_tel;
    }
    public void setNr(int nr_tel) {
        this.nr_tel = nr_tel;
    }

}
