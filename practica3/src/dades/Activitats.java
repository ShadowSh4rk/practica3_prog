package dades;

import java.time.LocalDate;

public class Activitats {
private String nom;    //nom de l'activitat
private String[]colectius;   //PDI, PTGAS, Estudiants
private LocalDate dataIniciInscripcio; 
private LocalDate dataFiInscripcio; 
private String tipus; // UnDia, Periodica, Online

    public Activitats(String nom, String[]colectius, LocalDate dataIniciInscripcio, LocalDate dataFiInscripcio, String tipus){
        this.nom=nom;
        this.colectius=colectius; 
        this.dataIniciInscripcio=dataIniciInscripcio; 
        this.dataFiInscripcio=dataFiInscripcio;
        this.tipus=tipus; 
    }
}
