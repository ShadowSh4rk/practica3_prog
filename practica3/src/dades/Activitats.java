package dades;

import java.util.Date;

public abstract class Activitats implements Laos {
private String nom;    //nom de l'activitat
private String[]colectius;   //PDI, PTGAS, Estudiants
private Date dataIniciInscripcio; 
private Date dataFiInscripcio; 
protected int limitPlaces;
private String tipus; // UnDia, Periodica, Online

    public Activitats(String nom, String[]colectius, Date dataIniciInscripcio, Date dataFiInscripcio, String tipus) {
        this.nom = nom;
        this.colectius = colectius; 
        this.dataIniciInscripcio = dataIniciInscripcio; 
        this.dataFiInscripcio = dataFiInscripcio;
        this.limitPlaces = Integer.MAX_VALUE;
        this.tipus = tipus;
    }

    public String getTipus() {
        return tipus;
    }

    public String getNom() {
        return nom;
    }

    public int getLimitPlaces() {
        return limitPlaces;
    }
}
