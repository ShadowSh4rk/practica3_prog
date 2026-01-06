package dades;

import java.io.Serializable;

import excepcions.*;

public class Inscripcio implements Serializable{
    private String nomActivitat;
    private String nomInscrit;
    private Data dataInscripcio;
    private int valoracio;

    //===============

    private Activitats activitat;

    public Activitats getActivitats() { //getter per activitat
        return activitat;
    }

    private Usuari usuari;

    public Usuari getUsuari() {
        return usuari;
    }

    /**
     * constructor de la classe inscripcio
     * 
     * @param nomActivitat
     * @param nomInscrit
     * @param dataInscripcio
     */
    public Inscripcio(String nomActivitat, String nomInscrit, Data dataInscripcio) {
        this.nomActivitat = nomActivitat;
        this.nomInscrit = nomInscrit;
        this.dataInscripcio = dataInscripcio;
        valoracio = 11;
    }

    public Data getDataInscripcio() {
        return dataInscripcio;
    }

    public String getNomActivitat() {
        return nomActivitat;
    }

    public String getNomInscrit() {
        return nomInscrit;
    }

    public int getValoracio() {
        return valoracio;
    }

    //setter per valoracio

    public void setValoracio(int valoracio){
        this.valoracio = valoracio;
    }

    //control valoracio
        public void assignarValoracio (int valoracio, Data avui) throws ActivitatNoFinalitzada, ForaDeRang{

            if(!this.getActivitats().haAcabat(avui)){
                throw new ActivitatNoFinalitzada();
            }

            if ((valoracio<0)||(valoracio>10)){
                throw new ForaDeRang();
            }

            //si tot esta be, afegim la valoracio
            this.setValoracio(valoracio);
        }


    public Inscripcio copia(){
        Inscripcio duplicat = new Inscripcio(nomActivitat, nomInscrit, dataInscripcio);
        return duplicat;
    }

    
}
