package dades;

/**
 * Representa una activitat online del programa Benestar URV.
 * Les activitats online són asíncrones, gratuïtes i sense límit de places.
 * Es guarda la data d'inici de l'activitat, el període de visualització i l'enllaç d'accés.
 */

public class ActivitatOnline extends Activitats {
    private Data dataInici; // Data d'inici de l'activitat online
    private int periodeVisualitzacio; // Nombre de dies que l'activitat estarà disponible
    private String enllac;  // Enllaç per accedir a l'activitat

    /**
     * Constructor de l'activitat online.
     *
     * @param nom Nom de l'activitat
     * @param colectius Col·lectius als quals s'ofereix l'activitat
     * @param dataIniciInscripcio Data d'inici del període d'inscripció
     * @param dataFiInscripcio Data final del període d'inscripció
     * @param dataInici Data d'inici de l'activitat
     * @param periodeVisualitzacio Nombre de dies que l'activitat estarà disponible
     * @param enllac Enllaç per accedir a l'activitat
     */

    public ActivitatOnline(String nom, String[]colectius, Data dataIniciInscripcio, Data dataFiInscripcio, Data dataInici, int periodeVisualitzacio, String enllac) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "Online");
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac; 
    }

    /**
     * Retorna l'enllaç per accedir a l'activitat.
     * @return Enllaç de l'activitat
     */
    public String getEnllac(){
        return enllac; 
    }

    /**
     * Les activitats online no tenen classe presencial, sempre retorna false.
     * @param avui Data a comprovar
     * @return false
     */
    @Override
    public boolean teClasseAvui(Data avui){
        return false; 
    }

    /**
     * Comprova si l'activitat està activa avui, tenint en compte la data d'inici i el període de visualització.
     * @param avui Data a comprovar
     * @return true si l'activitat està disponible avui, false altrament
     */
    @Override
    public boolean esActivaAvui(Data avui){
        Data dataFi=dataInici.afegirDies(periodeVisualitzacio);
        return !avui.esAnterior(dataInici) && !avui.esPosterior(dataFi);
    }

    /**
     * Retorna el preu de l'activitat online. Sempre és gratuïta.
     * @return 0
     */
    @Override
    public double getPreu(){
        return 0; 
    }

}
