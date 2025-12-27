package dades;

/**
 * Representa una activitat que es realitza en un sol dia.
 */

public class ActivitatUnDia extends Activitats {
    private Data data; 
    private String horari; 
    private String ciutat; 
    private double preu; 

     /**
     * Constructor de l'activitat d'un dia.
     *
     * @param nom Nom de l'activitat
     * @param colectius Col·lectius a qui s'ofereix (PDI, PTGAS, Estudiants)
     * @param dataIniciInscripcio Data d'inici del període d'inscripció
     * @param dataFiInscripcio Data final del període d'inscripció
     * @param data Data en què es realitza l'activitat
     * @param horari Horari de l'activitat
     * @param ciutat Ciutat on es realitza
     * @param limitPlaces Límits de places disponibles
     * @param preu Preu de l'activitat
     */
    public ActivitatUnDia(String nom, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio, Data data, String horari, String ciutat, int limitPlaces, double preu) {
        super(nom, colectius, dataIniciInscripcio, dataFiInscripcio, "UnDia");
        this.data = data;
        this.horari = horari;
        this.ciutat = ciutat; 
        this.limitPlaces=limitPlaces;
        this.preu = preu; 
    }
    /**
     * Retorna la ciutat on es realitza l'activitat.
     *
     * @return Nom de la ciutat
     */
    public String getCiutat(){
        return ciutat; 
    }
    /**
     * Retorna l'horari de l'activitat.
     *
     * @return Horari en format cadena (ex. "10:00-12:00")
     */
    public String getHorari(){
        return horari; 
    }

    /**
     * Retorna la data en què es realitza l'activitat.
     *
     * @return Data de l'activitat
     */ 
    public Data getData(){
        return data; 
    }
    /**
     * Indica si hi ha classe en la data indicada.
     *
     * @param avui Data a comprovar
     * @return true si hi ha classe avui, false en cas contrari
     */
    @Override
    public boolean teClasseAvui(Data avui){
        return avui.esIgual(data); 
    }

    /**
     * Indica si l'activitat està activa en la data indicada.
     *
     * @param avui Data a comprovar
     * @return true si l'activitat està activa avui, false en cas contrari
     */
    @Override
    public boolean esActivaAvui(Data avui){
        return avui.esIgual(data);
    }

    /**
     * Retorna el preu de l'activitat.
     *
     * @return Preu
     */

    @Override
    public double getPreu(){
        return preu; 
    }

    /**
    * Retorna una representació en format de línia de text de l'activitat d'un dia,
    * preparada per ser guardada en el fitxer de dades.
    *
    * El format és:
    * UnDia;[dades comunes de Activitats];dia;mes;any;horari;ciutat;preu
    *
    * On super.toString() aporta:
    * nom;colectius;dataIniciInscripcio;dataFiInscripcio;limitPlaces
    *
    * @return Cadena de text amb tots els atributs necessaris per reconstruir l'activitat.
    */
    @Override
    public String toString() {
    return "UnDia;" + super.toString() + ";" +
           data.getDia() + ";" + data.getMes() + ";" + data.getAny() + ";" +
           horari + ";" +
           ciutat + ";" +
           preu;
}


    
}
