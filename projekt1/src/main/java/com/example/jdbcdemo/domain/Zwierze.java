package com.example.jdbcdemo.domain;

public class Zwierze {

    private long zwierze_id;

    private String imie;
    private String gatunek;
    private String data_przyjecia;
    private long opiekun_id;
    private long wlasciciel_id;

    public Zwierze() {
    }

    public Zwierze(String imie, String gatunek, String data_przyjecia, long opiekun_id, long wlasciciel_id) {
        super();
        this.imie = imie;
        this.gatunek = gatunek;
        this.data_przyjecia = data_przyjecia;
        this.opiekun_id = opiekun_id;
        this.wlasciciel_id = wlasciciel_id;
    }
    public long getId() {
        return zwierze_id;
    }
    public void setId(long zwierze_id) {
        this.zwierze_id = zwierze_id;
    }
    public String getImie() {
        return imie;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void getImie(String imie) {
        this.imie = imie;
    }

    public void getGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public String getData_przyjecia() {
        return data_przyjecia;
    }

    public void getData_przyjecia(String data_przyjecia) {
        this.data_przyjecia = data_przyjecia;
    }

    public long getOpiekun_id() {
        return opiekun_id;
    }

    public void setOpiekun_id(long opiekun_id) {
        this.opiekun_id = opiekun_id;
    }

    public long getWlasciciel_id() {
        return wlasciciel_id;
    }

    public void getWlasciciel_id(long wlasciciel_id) {
        this.wlasciciel_id = wlasciciel_id;
    }

}
