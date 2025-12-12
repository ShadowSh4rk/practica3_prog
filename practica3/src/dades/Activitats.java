package dades;

import java.util.Date;

public abstract class Activitats {
private String nom;    //nom de l'activitat
private String[]colectius;   //PDI, PTGAS, Estudiants
private Date dataIniciInscripcio; 
private Date dataFiInscripcio; 
private String tipus; // UnDia, Periodica, Online

    public Activitats(String nom, String[]colectius, Date dataIniciInscripcio, Date dataFiInscripcio, String tipus) {
        this.nom = nom;
        this.colectius = colectius; 
        this.dataIniciInscripcio = dataIniciInscripcio; 
        this.dataFiInscripcio = dataFiInscripcio;
        this.tipus = tipus; 
    }
}
