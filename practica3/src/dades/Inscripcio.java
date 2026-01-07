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

    /**
     * 
     * @return activitat
     */
    public Activitats getActivitats() { //getter per activitat
        return activitat;
    }

    private Usuari usuari;

    /**
     * 
     * @return usuari
     */
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
        this.valoracio = 11; //placeholder per distingir les inscripcions valorades de les no valorades

        this.activitat=null;
        this.usuari=null;
    }

    /**
     * segon constructor de la classe inscripcio amb les instancies d'Usuari i d'Activitats
     * @param nomActivitat
     * @param nomInscrit
     * @param dataInscripcio
     * @param usuari
     * @param activitat
     */
    public Inscripcio(String nomActivitat, String nomInscrit, Data dataInscripcio, Usuari usuari, Activitats activitat) { 
        this.nomActivitat = nomActivitat; 
        this.nomInscrit = nomInscrit; 
        this.dataInscripcio = dataInscripcio; 
        this.valoracio = 11; 
        this.usuari = usuari; 
        this.activitat = activitat; 
    }

    /**
     * 
     * @return dataInscripcio
     */
    public Data getDataInscripcio() {
        return dataInscripcio;
    }

    /**
     * 
     * @return nomActivitat
     */
    public String getNomActivitat() {
        return nomActivitat;
    }

    /**
     * 
     * @return nomInscrit
     */
    public String getNomInscrit() {
        return nomInscrit;
    }

    /**
     * 
     * @return valoracio
     */
    public int getValoracio() {
        return valoracio;
    }


    /**
     * setter per l'atribut valoracio
     * @param valoracio
     */
    public void setValoracio(int valoracio){
        this.valoracio = valoracio;
    }

    /**
     * metode per a que un usuari valori una activitat al acabarla
     * @param valoracio
     * @param avui
     * @throws ActivitatNoFinalitzada
     * @throws ForaDeRang
     */
        public void assignarValoracio (int valoracio, Data avui) throws ActivitatNoFinalitzada, ForaDeRang{

            if (this.activitat == null || !this.activitat.haAcabat(avui)) { 
                throw new ActivitatNoFinalitzada(); 
            }

            if ((valoracio<0)||(valoracio>10)){
                throw new ForaDeRang();
            }

            //si tot esta be, afegim la valoracio
            this.setValoracio(valoracio);
        }


    /**
     * 
     * @return copia d'una instancia de tipus Inscripcio
     */
    public Inscripcio copia() { 
        Inscripcio dup = new Inscripcio(nomActivitat, nomInscrit, dataInscripcio, usuari, activitat); 
        dup.valoracio = this.valoracio; 
        return dup; 
    }

    
}
