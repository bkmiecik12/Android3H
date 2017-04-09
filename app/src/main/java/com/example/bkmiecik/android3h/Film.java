package com.example.bkmiecik.android3h;

/**
 * Created by bkmiecik on 08.04.17.
 */
public class Film {
    public String tytul;
    public String rezyser;
    public double ocena;
    public String kategoria;
    public String rok;

    public Film(String tytul, String rezyser, double ocena, String kategoria, String rok) {
        this.tytul = tytul;
        this.rezyser = rezyser;
        this.ocena = ocena;
        this.kategoria = kategoria;
        this.rok = rok;
    }
    @Override
    public String toString(){
        String film="";

        film+=tytul+"\n";
        film+=rezyser+"\n";
        film+=ocena+"\n";
        film+=kategoria+"\n";
        film+=rok+"\n";

        return film;
    }
}