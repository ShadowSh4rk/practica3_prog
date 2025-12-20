package dades;

import java.util.Date;

public class ActivitatUnDia extends Activitats {
    private Date data; 
    //private LocalTime horari; 
    private String ciutat; 
    private double preu; 

    public ActivitatUnDia(String nom, String[] colectius, Date dataIniciInscripcio, Date dataFiInscripcio, Date data, /*LocalTime horari,*/ String ciutat, int limitPlaces, double preu) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "UnDia");
        this.data = data;
        //this.horari = horari;
        this.ciutat = ciutat;
        this.limitPlaces = limitPlaces; 
        this.preu = preu; 
    }
}
