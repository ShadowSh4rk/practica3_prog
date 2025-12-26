package dades;

import java.util.*;

public abstract class Activitats implements Laos {
private String nom;    //nom de l'activitat
private String[]colectius;   //PDI, PTGAS, Estudiants
protected Date dataIniciInscripcio; 
protected Date dataFiInscripcio; 
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

    public Date getDataIniciInscripcio(){
        return dataIniciInscripcio; 
    }

    public Date getDataFiInscripcio(){
        return dataFiInscripcio; 
    }

    public String [] getColectius(){
        return colectius; 
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

    public boolean esEnPeriodeInscripcio(Date avui){
        return !avui.before(dataIniciInscripcio) && !avui.after(dataFiInscripcio); 
    }

    public boolean acceptaColectiu(String col){
        for(int i=0; i<colectius.length; i++){
            if(colectius[i].equalsIgnoreCase(col)){
                return true; 
            }
        }
        return false; 
    }

    public abstract int teClasseAvui(Date avui);
    public abstract int esActivaAvui(Date avui); 
    public abstract double getPreu(); 

    @Override 
    public String toString(){
        return nom+" (" +tipus+ ")"; 
    }
}
