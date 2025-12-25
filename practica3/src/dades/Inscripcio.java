package dades;

import java.util.*;

public class Inscripcio implements Laos{
    private String nomActivitat;
    private String nomInscrit;
    private Date dataInscripcio;
    private int valoracio;

    //===============

    private Activitats activitat;

    public Activitats getActivitats() { //getter per activitat
        return activitat;
    }

    /**
     * constructor de la classe inscripcio
     * 
     * @param nomActivitat
     * @param nomInscrit
     * @param dataInscripcio
     */
    public Inscripcio(String nomActivitat, String nomInscrit, Date dataInscripcio) {
        this.nomActivitat = nomActivitat;
        this.nomInscrit = nomInscrit;
        this.dataInscripcio = dataInscripcio;
        valoracio = 0;
    }

    public Date getDataInscripcio() {
        return dataInscripcio;
    }

    //setter per valoracio

    public void setValoracio(int valoracio){
        this.valoracio = valoracio;
    }

    public Inscripcio copia(){
        Inscripcio duplicat = new Inscripcio(nomActivitat, nomInscrit, dataInscripcio);
        return duplicat;
    }

    
}
