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

    /**
     * Retorna els col·lectius als quals s'ofereix l'activitat
     * @return Array de col·lectius
     */
    public String [] getColectius(){
        return colectius; 
    }

    /**
     * Retorna el tipus d'activitat: "UnDia", "Periodica", "Online"
     * @return Tipus d'activitat
     */
    public String getTipus() {
        return tipus;
    }

     /**
     * Retorna el nom de l'activitat.
     * @return Nom de l'activitat
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retorna el límit de places disponibles.
     * @return Límite de places
     */
    public int getLimitPlaces() {
        return limitPlaces;
    }

    public boolean esEnPeriodeInscripcio(Date avui){
        return !avui.before(dataIniciInscripcio) && !avui.after(dataFiInscripcio); 
    }

    /**
     * Comprova si l'activitat accepta un col·lectiu determinat.
     * @param col Nom del col·lectiu a comprovar
     * @return true si l'activitat accepta el col·lectiu, false altrament
     */
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

    /**
     * Retorna una representació en String de l'activitat.
     * @return Nom de l'activitat i tipus entre parèntesis
     */
    @Override 
    public String toString() {
        // Convertir el array de col·lectius a un String "PDI,PTGAS,Estudiants" 
        String col = ""; 
        for (int i = 0; i < colectius.length; i++) { 
            col += colectius[i]; 
            if (i < colectius.length - 1) { 
                col += ","; // añadir coma entre elementos 
                } 
            }
    return nom + ";" + col + ";" + dataIniciInscripcio.getDia() + ";" 
    + dataIniciInscripcio.getMes() + ";" + dataIniciInscripcio.getAny() + ";" 
    + dataFiInscripcio.getDia() + ";" + dataFiInscripcio.getMes() + ";" 
    + dataFiInscripcio.getAny() + ";" + limitPlaces; 
}
 }
    